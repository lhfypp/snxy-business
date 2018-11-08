package com.snxy.business.service;

import com.snxy.business.domain.CustomerMessage;

import java.util.List;

public interface CustomerMessageService {
    List<CustomerMessage> selectAllCustomerMessage();
}
