package com.snxy.business.dao.mapper;

import com.snxy.business.domain.BillInfo;
import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.domain.DriverOrderInfo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface DeliveryOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeliveryOrder record);

    int insertSelective(DeliveryOrder record);

    DeliveryOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeliveryOrder record);

    int updateByPrimaryKey(DeliveryOrder record);
    List<BillInfo> searchDeliveryOrder(@Param("sendPhones") List<String >sendPhones,@Param("orderStatus") String orderStatus,@Param("searchName") String searchName);
    DeliveryOrder selectBydDeliveryOrderId(@Param("deliveryOrderId") Long deliveryOrderId);

    List<BillInfo> selectDriverOrder(List list);

    DriverOrderInfo selectDriverOrderBydDeliveryOrderId(Long orderId);
}