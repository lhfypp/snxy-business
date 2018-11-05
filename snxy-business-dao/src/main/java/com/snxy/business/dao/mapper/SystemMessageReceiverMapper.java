package com.snxy.business.dao.mapper;

public interface SystemMessageReceiverMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMessageReceiver record);

    int insertSelective(SystemMessageReceiver record);

    SystemMessageReceiver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemMessageReceiver record);

    int updateByPrimaryKey(SystemMessageReceiver record);
}