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

    void updateNameBySystemUserId(@Param("systemUserId") Long systemUserId, @Param("name") String name);

    OnlineUser selectByPhone(String phone);

    void insertOnlineUser(OnlineUser onlineUser);

    List<OnlineUser> selectByPhoneList(@Param("phoneList") List<String> phoneList);

    void insertOnlineUserList(List<OnlineUser> list);

    List<OnlineUser> selectByOnlineUserIdList(@Param("onlineUserIdList") List<Long> onlineUserIdList);
}