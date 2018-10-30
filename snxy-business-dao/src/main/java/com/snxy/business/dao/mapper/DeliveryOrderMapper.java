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


    DeliveryOrder selectBydDeliveryOrderId(@Param("deliveryOrderId") Long deliveryOrderId);
    void  cancelOrderByOrderId(@Param("orderId")Long orderId,@Param("status")Integer status);

    void updateEndLoading(@Param("deliveryOrderId") Long deliveryOrderId, @Param("loadStatus") Integer loadStatus);


    void updateLocationCertificate(@Param("productionCertificate") Long productionCertificate, @Param("qualitied") Integer qualitied);

    void updateQualityCertificate(@Param("qualityCertificateId") Long qualityCertificateId, @Param("qualitied") Integer qualitied);

    void updateLoadStatus(@Param("deliveryOrderId") Long deliveryOrderId, @Param("status") Integer status);

    List<BillInfo> selectDriverOrderByOderId(List orderIdList);

}