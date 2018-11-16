package com.snxy.business.service;

import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.service.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DeliveryOrderService {
//    BillVO createOrderNo(Long onlineUserId);

    void saveDeliveryOrder(SystemUserVO systemUserVO, DeliveryOrderVo deliveryOrderVo);

    HomePageOrderVO showNewestOrder(SystemUserVO systemUserVO);

    void confirmationOrder(SystemUserVO systemUserVO,DriverConfirmationVO driverConfirmationVO);

    DeliveryOrder showOrderDetails(Long deliveryOrderId);

    List<DeliveryOrder> selectByCompanyId(Long companyId);
}
