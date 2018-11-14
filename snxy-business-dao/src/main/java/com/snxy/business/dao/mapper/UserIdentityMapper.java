package com.snxy.business.dao.mapper;

import com.snxy.business.domain.UserIdentity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserIdentityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserIdentity record);

    int insertSelective(UserIdentity record);

    UserIdentity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserIdentity record);

    int updateByPrimaryKey(UserIdentity record);

    void insertIdentity(UserIdentity userIdentity);

    void insertIdentityList(List<UserIdentity> list);

    UserIdentity selectByOnlineUserId(@Param("onlineUserId") Long onlineUserId,@Param("identityId") Integer identityId);

    void updateIdentity(@Param("onlineUserId") Long onlineUserId, @Param("identityId") Integer identityId);
}