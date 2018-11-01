package com.snxy.business.dao.mapper;

import com.snxy.business.domain.OnlineUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OnlineUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OnlineUser record);

    int insertSelective(OnlineUser record);

    OnlineUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OnlineUser record);

    int updateByPrimaryKey(OnlineUser record);
    List<String> searchphones(@Param("onlineUserIds") List<Long> onlineUserIds);
<<<<<<< HEAD
    OnlineUser selectBySystemUserId(@Param("systemUserId")long systemUserId);
=======

    List<OnlineUser> selectByOnlineUserIdList(@Param("onlineUserIdList") List<Long> onlineUserIdList);

    OnlineUser selectByPhone(String phone);

    OnlineUser selectByName(String name);
>>>>>>> d1aef324a1116338d372624d22129509a4e81ae8
}