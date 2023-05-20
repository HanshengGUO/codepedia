package com.hsguo.codepedia.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsguo.codepedia.domain.Ebook;
import com.hsguo.codepedia.domain.EbookExample;
import com.hsguo.codepedia.mapper.EbookMapper;
import com.hsguo.codepedia.req.EbookQueryReq;
import com.hsguo.codepedia.req.EbookSaveReq;
import com.hsguo.codepedia.resp.EbookQueryResp;
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
public class EbookService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
    // 也可以用Autowired注解
    // Resource是JDK自带的
    // Autowired是来自Spring
    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list(EbookQueryReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName()))
            criteria.andNameLike("%" + req.getName() + "%");
        if (!ObjectUtils.isEmpty(req.getCategoryId2()))
            criteria.andCategory2IdEqualTo(req.getCategoryId2());
        // 只对第一次查询的sql有效，所以尽量和查询语句放在一起
        // 分页的基础数据四条，返回给前端计算
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooksList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        PageResp<EbookQueryResp> objectPageResp = new PageResp<EbookQueryResp>();
        // null 相当于 new EbookExample()
        List<EbookQueryResp> ebooksRespList = CopyUtil.copyList(ebooksList, EbookQueryResp.class);
//        List<EbookResp> ebooksRespList = new ArrayList<>();
//        for (Ebook ebook : ebooksList) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//            ebooksRespList.add(ebookResp);
//        }
        objectPageResp.setTotal(pageInfo.getTotal());
        objectPageResp.setList(ebooksRespList);
        return objectPageResp;
    }

    /**
     * 保存更新数据
     */
    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            ebook.setId(snowFlake.nextId());
            ebook.setDocCount(0);
            ebook.setViewCount(0);
            ebook.setVoteCount(0);
            ebookMapper.insert(ebook);
        } else {
            // 更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    public void delete(Long id) {
        ebookMapper.deleteByPrimaryKey(id);
    }
}
