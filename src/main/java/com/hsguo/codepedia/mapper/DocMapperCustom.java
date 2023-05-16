package com.hsguo.codepedia.mapper;

import org.apache.ibatis.annotations.Param;

public interface DocMapperCustom {
    public void increaseViewCount(@Param("id") Long id);

    public void increaseVoteCount(@Param("id") Long id);
}
