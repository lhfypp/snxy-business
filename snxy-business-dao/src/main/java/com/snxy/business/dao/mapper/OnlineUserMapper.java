package com.snxy.business.dao.mapper;

import com.snxy.business.domain.OnlineUser;

import java.util.List;
import java.util.Map;

public interface OnlineUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OnlineUser record);

    int insertSelective(OnlineUser record);

    OnlineUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OnlineUser record);

    int updateByPrimaryKey(OnlineUser record);
    long selectIdBySystemUserID(String SystemUserId);

}