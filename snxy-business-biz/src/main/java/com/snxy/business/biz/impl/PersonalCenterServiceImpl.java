package com.snxy.business.biz.impl;

import com.snxy.business.domain.CommonProblems;
import com.snxy.business.domain.CustomerMessage;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.CustomerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class PersonalCenterServiceImpl implements PersonalCenterService {

    @Resource
    private CustomerMessageService customerMessageService;
    @Resource
    private CommonProblemsService commonProblemsService;
    @Resource
    private SystemUserInfoService systemUserInfoService;
    @Override
    public CustomerVO myCustomer(Long systemUserId) {

        List<CustomerMessage> customerMessageList = customerMessageService.selectAllCustomerMessage();
        List<CommonProblems> problemsList = commonProblemsService.selectAllCommonProblems();
        String mobile = systemUserInfoService.selectMobileBySystemUserId(systemUserId);
        CustomerVO customerVO = CustomerVO.builder()
                .customerMessageList(customerMessageList)
                .commonProblemsList(problemsList)
                .mobile(mobile)
                .build();
        return customerVO;
    }
}
