package com.snxy.business.dao.mapper;

import com.snxy.business.domain.OnlineUser;

import com.snxy.business.domain.OnlineUser;

import java.util.List;

import java.util.Map;


import org.apache.ibatis.annotations.Param;


public interface OnlineUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OnlineUser record);

    int insertSelective(OnlineUser record);

    OnlineUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OnlineUser record);

    int updateByPrimaryKey(OnlineUser record);

    long selectIdBySystemUserID(String SystemUserId);



    void updateNameBySystemUserId(@Param("systemUserId") Long systemUserId, @Param("name") String name);

    OnlineUser selectByPhone(String phone);

    void insertOnlineUser(OnlineUser onlineUser);

    List<OnlineUser> selectByPhoneList(@Param("phoneList") List<String> phoneList);

    void insertOnlineUserList(List<OnlineUser> list);

    List<OnlineUser> selectByOnlineUserIdList(@Param("onlineUserIdList") List<Long> onlineUserIdList);

    Long selectOnlineUserIdBySystemUserId(Long systemUserId);

    void updateOnlineMobile(@Param("onlineUserId") Long onlineUserId ,@Param("mobile") String newMobile);

}