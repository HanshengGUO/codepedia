package com.hsguo.codepedia.service;

import com.hsguo.codepedia.mapper.EbookSnapshotMapperCustom;
import com.hsguo.codepedia.resp.StatisticResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookSnapshotService {

    @Resource
    private EbookSnapshotMapperCustom ebookSnapshotMapperCustom;

    public void genSnapshot() {
        ebookSnapshotMapperCustom.genSnapshot();
    }

    /**
     * 获取首页数值数据：总阅读数、总点赞数、今日阅读数、今日点赞数、今日预计阅读数、今日预计阅读增长
     */
    public List<StatisticResp> getStatistic() {
        return ebookSnapshotMapperCustom.getStatistic();
    }

}
