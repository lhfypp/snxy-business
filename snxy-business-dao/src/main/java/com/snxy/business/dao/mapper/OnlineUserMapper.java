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
    OnlineUser selectBySystemUserId(@Param("systemUserId")long systemUserId);
}