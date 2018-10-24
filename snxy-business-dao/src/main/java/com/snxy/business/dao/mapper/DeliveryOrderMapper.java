package com.snxy.business.dao.mapper;

import com.snxy.business.domain.BillInfo;
import com.snxy.business.domain.BillInfoDetail;
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


    List<BillInfo> selectDriverOrder(List list);

    DriverOrderInfo selectDriverOrderBydDeliveryOrderId(Long orderId);

    BillInfoDetail selectBydDeliveryOrderId(@Param("deliveryOrderId") Long deliveryOrderId);
    void  cancelOrderByOrderId(@Param("orderId")Long orderId,@Param("status")Integer status);

    void updateEndLoading(Long deliveryOrderId);


    void updateLocationCertificate(@Param("productionCertificate") Long productionCertificate, @Param("qualitied") Integer qualitied);

    void updateQualityCertificate(@Param("qualityCertificateId") Long qualityCertificateId, @Param("qualitied") Integer qualitied);

    void updateLoadStatus(@Param("deliveryOrderId") Long deliveryOrderId, @Param("loadStatus") Integer loadStatus);
}