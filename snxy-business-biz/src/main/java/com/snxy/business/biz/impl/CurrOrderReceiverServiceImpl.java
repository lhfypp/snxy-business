package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CurrOrderReceiverMapper;
import com.snxy.business.domain.CurrOrderReceiver;
import com.snxy.business.domain.DriverPartInfo;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.CurrOrderReceiverService;
import com.snxy.business.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CurrOrderReceiverServiceImpl implements CurrOrderReceiverService {
    @Resource
    private CurrOrderReceiverMapper currOrderReceiverMapper;
    @Resource
    private VehicleService vehicleService;
    @Override
    public DriverPartInfo selectDriverPartInfo(long orderId) {
        DriverPartInfo driverPartInfo=new DriverPartInfo();
        //查询出司机手机号，司机姓名
        CurrOrderReceiver currOrderReceiver=currOrderReceiverMapper.selectDriverMessageByOrderId(orderId);
        //查询出车辆信息
        Vehicle Vehicle=new Vehicle();
        if(currOrderReceiver!=null&&currOrderReceiver.getVehicleId()!=null) {
            Vehicle= vehicleService.searchVehicleByVehicleId(currOrderReceiver.getVehicleId());
            driverPartInfo.setPlateNumber(Vehicle.getPlateNumber());
            driverPartInfo.setCarTypeDesc(Vehicle.getTonnage());
        }
        driverPartInfo.setDriverMobile(currOrderReceiver.getDriverMobile());
        driverPartInfo.setDriverName(currOrderReceiver.getDriverName());
        return driverPartInfo;
    }

    @Override
    public void tranferOrder(long orderId, String driveMobile, String driverName) {
        currOrderReceiverMapper.tranferOrder(orderId,driveMobile,driverName);
    }

    @Override
    public void updateCurrOrderReceiver(String OrderId, String vehicleId) {
        currOrderReceiverMapper.updateCurrOrderReceiver(OrderId,vehicleId);
    }

    @Override
    public List selectOrderIdByDriverMobile(String driverMobile) {
        return currOrderReceiverMapper.selectOrderIdByDriverMobile(driverMobile);
    }

    @Override
    public void changeDriver(long orderId, String driverName, String drivePhone) {
        currOrderReceiverMapper.changeDriver(orderId,driverName,drivePhone);
    }
	 @Override
    public CurrOrderReceiver selectDriverMessageByOrderId(Long deliveryOrderId) {
        CurrOrderReceiver currOrderReceiver = currOrderReceiverMapper.selectDriverMessageByOrderId(deliveryOrderId);
        return currOrderReceiver;
    }
    public void updateByAgent(DriverPartInfo driverPartInfo){
        currOrderReceiverMapper.updateByAgent(driverPartInfo);
    }
}
