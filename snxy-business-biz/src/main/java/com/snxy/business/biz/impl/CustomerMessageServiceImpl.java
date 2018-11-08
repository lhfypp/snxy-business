package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CustomerMessageMapper;
import com.snxy.business.domain.CustomerMessage;
import com.snxy.business.service.CustomerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CustomerMessageServiceImpl implements CustomerMessageService {
    @Resource
    private CustomerMessageMapper customerMessageMapper;

    /**
     * 查询留言列表
     * @return
     */
    @Override
    public List<CustomerMessage> selectAllCustomerMessage() {
       List<CustomerMessage> customerMessageList = customerMessageMapper.selectAllCustomerMessage();
        return customerMessageList;
    }
}
