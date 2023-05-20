package com.hsguo.codepedia.mapper;

import com.hsguo.codepedia.resp.StatisticResp;

import java.util.List;

public interface EbookSnapshotMapperCustom {

    public void genSnapshot();

    List<StatisticResp> getStatistic();
}