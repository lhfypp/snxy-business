package com.snxy.business.service;

import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.domain.EntranceFeeDetail;
import com.snxy.common.util.PageInfo;

import java.util.List;

import com.snxy.business.service.vo.*;

public interface DeliveryOrderService {
//    BillVO createOrderNo(Long onlineUserId);
    PageInfo<DeliveryOrder> selectByCreatorId(Long onlineUserId, Long status);

    String selectByOrderNo(String orderNo);

    String chargeCount(ChargeCountVO chargeCountVO);

   /* BillVO createOrderNo(Long onlineUserId);
    String chargeCount(EntranceFeeDetail entranceFeeDetail);*/

    void saveDeliveryOrder(SystemUserVO systemUserVO, DeliveryOrderVo deliveryOrderVo);

    HomePageOrderVO showNewestOrder(SystemUserVO systemUserVO);

    void confirmationOrder(SystemUserVO systemUserVO, DriverConfirmationVO driverConfirmationVO);

    DeliveryOrderMessageVO showOrderDetails(Long deliveryOrderId);

    List<DeliveryOrder> selectByCompanyId(Long companyId);
    DeliveryOrder searchDeliveryOrderById(Long id);

    DeliveryOrder selectDriverNumByOrderNo(String orderNo);

    Long selectDeliveryIdByOrderNo(String orderNo);

    List<Long> selectDeliveryIdByOrderList(List<String> orderNoList);


    PageInfo<HomePageOrderVO> showOrderList(SystemUserVO systemUserVO, Integer pageNum, Integer pageSize, Integer status);
}

