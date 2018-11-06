package com.snxy.business.dao.mapper;

import com.snxy.business.domain.OnlineUser;
import org.apache.ibatis.annotations.Param;


public interface OnlineUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OnlineUser record);

    int insertSelective(OnlineUser record);

    OnlineUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OnlineUser record);

    int updateByPrimaryKey(OnlineUser record);

    void updateNameBySystemUserId(@Param("systemUserId") Long systemUserId, @Param("name") String name);

    Long selectOnlineUserIdBySystemUserId(Long systemUserId);
}