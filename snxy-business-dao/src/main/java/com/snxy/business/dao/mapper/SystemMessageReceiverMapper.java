package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SystemMessageReceiver;

public interface SystemMessageReceiverMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMessageReceiver record);

    int insertSelective(SystemMessageReceiver record);

    SystemMessageReceiver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemMessageReceiver record);

    int updateByPrimaryKey(SystemMessageReceiver record);
}