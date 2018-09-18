package com.snxy.business.dao.mapper;

import com.snxy.business.domain.FriendGroup;

public interface FriendGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FriendGroup record);

    int insertSelective(FriendGroup record);

    FriendGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FriendGroup record);

    int updateByPrimaryKey(FriendGroup record);
}