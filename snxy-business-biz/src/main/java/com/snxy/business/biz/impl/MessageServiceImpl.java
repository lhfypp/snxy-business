package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.MessageMapper;
import com.snxy.business.domain.Message;
import com.snxy.business.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageMapper messageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMessage(Message message) {
        messageMapper.insertSelective(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMessageList(List<Message> messageList) {
        messageMapper.insertMessageList(messageList);
    }
}
