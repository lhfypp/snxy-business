package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CurrOrderReceiver;
import com.snxy.business.domain.DriverPartInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CurrOrderReceiverMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CurrOrderReceiver record);

    int insertSelective(CurrOrderReceiver record);

    CurrOrderReceiver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CurrOrderReceiver record);

    int updateByPrimaryKey(CurrOrderReceiver record);

    CurrOrderReceiver selectDriverMessageByOrderId(Long deliveryOrderId);

    void changeDriver(@Param("orderId") long orderId, @Param("driverName") String driverName,@Param("drivePhone") String drivePhone);

    void updateCurrOrderReceiver(@Param("OrderId") String OrderId, @Param("vehicleId") String vehicleId) ;

    void tranferOrder(@Param("orderId") long orderId,@Param("driveMobile") String driveMobile ,@Param("driverName") String driverName);

    List selectOrderIdByDriverMobile(Long driverMobile);

    void updateByAgent(@Param("driverPartInfo") DriverPartInfo driverPartInfo);

    List<Long> selectDriverMobile(String driverMobile);

    List<Long> selectCurrList(List<Long> list);

}