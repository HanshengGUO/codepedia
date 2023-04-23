package com.hsguo.codepedia.service;

import com.hsguo.codepedia.domain.Ebook;
import com.hsguo.codepedia.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    // 也可以用Autowired注解
    // Resource是JDK自带的
    // Autowired是来自Spring
    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list(){
        return ebookMapper.selectByExample(null);
        // null 相当于 new EbookExample()
    }
}
