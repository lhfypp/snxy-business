package com.snxy.business.dao.mapper;

import com.snxy.business.domain.*;
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
    BillInfoDetail selectBydDeliveryOrderId(@Param("deliveryOrderId") String deliveryOrderId);
    void  cancelOrderByOrderId(@Param("orderNo")String orderId,@Param("status") int status);//通过订单Id

    void updateOrder(@Param("billInfoDetail")UpdateBillInfoDetail billInfoDetail );


}