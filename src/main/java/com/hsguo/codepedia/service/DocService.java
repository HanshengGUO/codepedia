package com.hsguo.codepedia.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsguo.codepedia.domain.Content;
import com.hsguo.codepedia.domain.Doc;
import com.hsguo.codepedia.domain.DocExample;
import com.hsguo.codepedia.mapper.ContentMapper;
import com.hsguo.codepedia.mapper.DocMapper;
import com.hsguo.codepedia.mapper.DocMapperCustom;
import com.hsguo.codepedia.req.DocQueryReq;
import com.hsguo.codepedia.req.DocSaveReq;
import com.hsguo.codepedia.resp.DocQueryResp;
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
public class DocService {
    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);
    // 也可以用Autowired注解
    // Resource是JDK自带的
    // Autowired是来自Spring
    @Resource
    private DocMapper docMapper;

    @Resource
    private DocMapperCustom docMapperCustom;

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<DocQueryResp> list(DocQueryReq req) {
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        // 只对第一次查询的sql有效，所以尽量和查询语句放在一起
        // 分页的基础数据四条，返回给前端计算
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> docsList = docMapper.selectByExample(docExample);

        PageInfo<Doc> pageInfo = new PageInfo<>(docsList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        PageResp<DocQueryResp> objectPageResp = new PageResp<DocQueryResp>();
        // null 相当于 new DocExample()
        List<DocQueryResp> docsRespList = CopyUtil.copyList(docsList, DocQueryResp.class);
//        List<DocResp> docsRespList = new ArrayList<>();
//        for (Doc doc : docsList) {
//            DocResp docResp = new DocResp();
//            BeanUtils.copyProperties(doc, docResp);
//            docsRespList.add(docResp);
//        }
        objectPageResp.setTotal(pageInfo.getTotal());
        objectPageResp.setList(docsRespList);
        return objectPageResp;
    }

    public List<DocQueryResp> all(Long ebookId) {
        DocExample docExample = new DocExample();
        docExample.createCriteria().andEbookIdEqualTo(ebookId);
        docExample.setOrderByClause("sort asc");
//        DocExample.Criteria criteria = docExample.createCriteria();
        // 只对第一次查询的sql有效，所以尽量和查询语句放在一起
        // 分页的基础数据四条，返回给前端计算
//        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> docsList = docMapper.selectByExample(docExample);

//        PageInfo<Doc> pageInfo = new PageInfo<>(docsList);
//        LOG.info("总行数：{}", pageInfo.getTotal());
//        LOG.info("总页数：{}", pageInfo.getPages());
//
//        PageResp<DocQueryResp> objectPageResp = new PageResp<DocQueryResp>();
        // null 相当于 new DocExample()
        List<DocQueryResp> docsRespList = CopyUtil.copyList(docsList, DocQueryResp.class);
//        List<DocResp> docsRespList = new ArrayList<>();
//        for (Doc doc : docsList) {
//            DocResp docResp = new DocResp();
//            BeanUtils.copyProperties(doc, docResp);
//            docsRespList.add(docResp);
//        }
//        objectPageResp.setTotal(pageInfo.getTotal());
//        objectPageResp.setList(docsRespList);
        return docsRespList;
    }

    /**
     * 保存更新数据
     */
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = CopyUtil.copy(req, Content.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            doc.setId(snowFlake.nextId());
//            doc.setDocCount(0);
//            doc.setViewCount(0);
//            doc.setVoteCount(0);
            docMapper.insert(doc);
            content.setId(doc.getId());
            contentMapper.insert(content);
        } else {
            // 更新
            docMapper.updateByPrimaryKey(doc);
            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if (count == 0) {
                contentMapper.insert(content);
            }
        }
    }

    public void delete(Long id) {
        docMapper.deleteByPrimaryKey(id);
    }

    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        // 文档阅读数+1
        docMapperCustom.increaseViewCount(id);
        if (content == null)
            return "";
        return content.getContent();
    }

    public void vote(Long id) {
        docMapperCustom.increaseVoteCount(id);
    }

    public void delete(List<String> ids) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }
}
