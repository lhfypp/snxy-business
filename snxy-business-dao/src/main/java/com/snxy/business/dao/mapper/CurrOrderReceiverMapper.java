package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CurrOrderReceiver;
import com.snxy.business.domain.InsertCurrentOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CurrOrderReceiverMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CurrOrderReceiver record);

    int insertSelective(CurrOrderReceiver record);

    CurrOrderReceiver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CurrOrderReceiver record);

    int updateByPrimaryKey(CurrOrderReceiver record);

    List selectOrderIdByPrimaryKey(Long userId);
<<<<<<< HEAD

    CurrOrderReceiver selectDriverMessageByOrderId(Long deliveryOrderId);
=======
    void changeDriver(@Param("orderId") long orderId, @Param("driverName") String driverName,@Param("drivePhone") String drivePhone);
    void insertCurrentOrder(@Param("insertCurrentOrder") InsertCurrentOrder insertCurrentOrder);
    void updateCurrOrderReceiver(@Param("OrderId") String OrderId, @Param("vehicleId") String vehicleId) ;
    void tranferOrder(@Param("orderId") long orderId,@Param("driveMobile") String driveMobile ,@Param("driverName") String driverName);
>>>>>>> ed292f6fe7171cb372977c43215ac1bb5f3ce5e2
}