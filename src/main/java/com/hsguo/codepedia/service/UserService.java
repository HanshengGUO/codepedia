package com.hsguo.codepedia.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsguo.codepedia.domain.User;
import com.hsguo.codepedia.domain.UserExample;
import com.hsguo.codepedia.exception.BusinessException;
import com.hsguo.codepedia.exception.BusinessExceptionCode;
import com.hsguo.codepedia.mapper.UserMapper;
import com.hsguo.codepedia.req.UserQueryReq;
import com.hsguo.codepedia.req.UserResetPasswordReq;
import com.hsguo.codepedia.req.UserSaveReq;
import com.hsguo.codepedia.resp.PageResp;
import com.hsguo.codepedia.resp.UserQueryResp;
import com.hsguo.codepedia.utils.CopyUtil;
import com.hsguo.codepedia.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    // 也可以用Autowired注解
    // Resource是JDK自带的
    // Autowired是来自Spring
    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<UserQueryResp> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getLoginName()))
            criteria.andNameLike("%" + req.getLoginName() + "%");
        // 只对第一次查询的sql有效，所以尽量和查询语句放在一起
        // 分页的基础数据四条，返回给前端计算
        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> usersList = userMapper.selectByExample(userExample);

        PageInfo<User> pageInfo = new PageInfo<>(usersList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        PageResp<UserQueryResp> objectPageResp = new PageResp<UserQueryResp>();
        // null 相当于 new UserExample()
        List<UserQueryResp> usersRespList = CopyUtil.copyList(usersList, UserQueryResp.class);
//        List<UserResp> usersRespList = new ArrayList<>();
//        for (User user : usersList) {
//            UserResp userResp = new UserResp();
//            BeanUtils.copyProperties(user, userResp);
//            usersRespList.add(userResp);
//        }
        objectPageResp.setTotal(pageInfo.getTotal());
        objectPageResp.setList(usersRespList);
        return objectPageResp;
    }

    /**
     * 保存更新数据
     */
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            User userDB = selectByLoginName(req.getLoginName());
            if (ObjectUtils.isEmpty(userDB)) {
                // 新增
                user.setId(snowFlake.nextId());
                userMapper.insert(user);
            } else {
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }


        } else {
            // 更新
            // 先清空loginName
            user.setLoginName(null);
            user.setPassword(null);
            // 这里不更新为null的字段
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    /**
     * 修改密码
     */
    public void resetPassword(UserResetPasswordReq req) {
        User user = CopyUtil.copy(req, User.class);
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public User selectByLoginName(String LoginName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(LoginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }
}
