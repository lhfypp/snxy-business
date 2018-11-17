package com.snxy.business.service;

import com.snxy.business.service.vo.CustomerVO;

public interface PersonalCenterService {

    CustomerVO myCustomer(Long systemUserId);
}
