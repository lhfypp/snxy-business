package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Identy;
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

    void deleteIdentity(@Param("onlineUserId") Long onlineUserId);

    void insertBatch(@Param("list") List<UserIdentity> userIdentities);

    List<Identy> selectUserIdentyByOnlineUserIdList(@Param("onlineUserIdList") List<Long> onlineUserIdList);

    void updateByOnlineUserIdList(@Param("list") List<UserIdentity> userIdentityList);

    void updateByOnlineUserId(@Param("onlineUserId") Long onlineUserId, @Param("isDelete") byte isDelete);
}