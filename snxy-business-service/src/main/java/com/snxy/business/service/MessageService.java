package com.snxy.business.service;

import com.snxy.business.domain.Message;

import java.util.List;

public interface MessageService {
    void insertMessage(Message message);

    void insertMessageList(List<Message> messageList);
}
