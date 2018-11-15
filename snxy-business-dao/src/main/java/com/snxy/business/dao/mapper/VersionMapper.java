package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Version;
import org.apache.ibatis.annotations.Param;

public interface VersionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Version record);

    int insertSelective(Version record);

    Version selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);

    void insertRelease(@Param("systemUserId") Long systemUserId, @Param("versionNum") String versionNum, @Param("url") String url);

    String selectUrlBySystemUserId(Long systemUserId);
}