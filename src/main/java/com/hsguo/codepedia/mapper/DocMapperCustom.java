package com.hsguo.codepedia.mapper;

import org.apache.ibatis.annotations.Param;

public interface DocMapperCustom {
    public void increaseViewCount(@Param("id") Long id);
}
