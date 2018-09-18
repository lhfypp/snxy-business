package com.snxy.business.dao.mapper;

import com.snxy.business.domain.ChatLog;

public interface ChatLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChatLog record);

    int insertSelective(ChatLog record);

    ChatLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChatLog record);

    int updateByPrimaryKey(ChatLog record);
}