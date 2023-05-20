package com.hsguo.codepedia.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsguo.codepedia.domain.Category;
import com.hsguo.codepedia.domain.CategoryExample;
import com.hsguo.codepedia.mapper.CategoryMapper;
import com.hsguo.codepedia.req.CategoryQueryReq;
import com.hsguo.codepedia.req.CategorySaveReq;
import com.hsguo.codepedia.resp.CategoryQueryResp;
import com.hsguo.codepedia.resp.PageResp;
import com.hsguo.codepedia.utils.CopyUtil;
import com.hsguo.codepedia.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    // 也可以用Autowired注解
    // Resource是JDK自带的
    // Autowired是来自Spring
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        // 只对第一次查询的sql有效，所以尽量和查询语句放在一起
        // 分页的基础数据四条，返回给前端计算
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categorysList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        PageResp<CategoryQueryResp> objectPageResp = new PageResp<CategoryQueryResp>();
        // null 相当于 new CategoryExample()
        List<CategoryQueryResp> categorysRespList = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
//        List<CategoryResp> categorysRespList = new ArrayList<>();
//        for (Category category : categorysList) {
//            CategoryResp categoryResp = new CategoryResp();
//            BeanUtils.copyProperties(category, categoryResp);
//            categorysRespList.add(categoryResp);
//        }
        objectPageResp.setTotal(pageInfo.getTotal());
        objectPageResp.setList(categorysRespList);
        return objectPageResp;
    }

    public List<CategoryQueryResp> all() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
//        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        // 只对第一次查询的sql有效，所以尽量和查询语句放在一起
        // 分页的基础数据四条，返回给前端计算
//        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);

//        PageInfo<Category> pageInfo = new PageInfo<>(categorysList);
//        LOG.info("总行数：{}", pageInfo.getTotal());
//        LOG.info("总页数：{}", pageInfo.getPages());
//
//        PageResp<CategoryQueryResp> objectPageResp = new PageResp<CategoryQueryResp>();
        // null 相当于 new CategoryExample()
        List<CategoryQueryResp> categorysRespList = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
//        List<CategoryResp> categorysRespList = new ArrayList<>();
//        for (Category category : categorysList) {
//            CategoryResp categoryResp = new CategoryResp();
//            BeanUtils.copyProperties(category, categoryResp);
//            categorysRespList.add(categoryResp);
//        }
//        objectPageResp.setTotal(pageInfo.getTotal());
//        objectPageResp.setList(categorysRespList);
        return categorysRespList;
    }

    /**
     * 保存更新数据
     */
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            category.setId(snowFlake.nextId());
//            category.setDocCount(0);
//            category.setViewCount(0);
//            category.setVoteCount(0);
            categoryMapper.insert(category);
        } else {
            // 更新
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    public void delete(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
