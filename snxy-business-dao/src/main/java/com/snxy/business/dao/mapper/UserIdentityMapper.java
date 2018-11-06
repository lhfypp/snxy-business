package com.snxy.business.dao.mapper;

import com.snxy.business.domain.UserIdentity;
import org.apache.ibatis.annotations.Param;

public interface UserIdentityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserIdentity record);

    int insertSelective(UserIdentity record);

    UserIdentity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserIdentity record);

    int updateByPrimaryKey(UserIdentity record);

    void insertIdentity(@Param("onlineUserId") Long onlineUserId, @Param("identityId") Integer identityId);
}