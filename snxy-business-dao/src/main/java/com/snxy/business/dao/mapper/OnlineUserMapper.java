package com.snxy.business.dao.mapper;

import com.snxy.business.domain.OnlineUser;

public interface OnlineUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OnlineUser record);

    int insertSelective(OnlineUser record);

    OnlineUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OnlineUser record);

    int updateByPrimaryKey(OnlineUser record);

    Long selectOnlineIdBySystemId(Long systemUserId);

    void updateNameById(Long onlineUserId, String userName);
}