package com.hsguo.codepedia.service;

import com.hsguo.codepedia.domain.Ebook;
import com.hsguo.codepedia.domain.EbookExample;
import com.hsguo.codepedia.mapper.EbookMapper;
import com.hsguo.codepedia.req.EbookReq;
import com.hsguo.codepedia.resp.EbookResp;
import com.hsguo.codepedia.utils.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    // 也可以用Autowired注解
    // Resource是JDK自带的
    // Autowired是来自Spring
    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName()))
            criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);
        // null 相当于 new EbookExample()
        List<EbookResp> ebooksRespList = CopyUtil.copyList(ebooksList, EbookResp.class);
//        List<EbookResp> ebooksRespList = new ArrayList<>();
//        for (Ebook ebook : ebooksList) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//            ebooksRespList.add(ebookResp);
//        }
        return ebooksRespList;
    }
}
