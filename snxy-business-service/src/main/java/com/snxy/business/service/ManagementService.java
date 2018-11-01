package com.snxy.business.service;

import com.snxy.business.domain.CheckBillInfo;
import com.snxy.business.domain.CreateCheckBillVO;

import java.util.List;

public interface ManagementService {

    void save(CreateCheckBillVO createcCheckBillVO);

    List<CheckBillInfo> selectCheckBillList(String driverMobile);

    CheckBillInfo selectCheckBillByUserId(String deliveryOrderId);

    List selectVague(String vegetableCategoryName);
}
