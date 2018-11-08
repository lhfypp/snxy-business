package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VehicleGpsRecordMapper;
import com.snxy.business.domain.VehicleGpsRecord;
import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.VehicleGpsRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class VehicleGpsRecordServiceImpl implements VehicleGpsRecordService {
    @Resource
    private VehicleGpsRecordMapper vehicleGpsRecordMapper;
    @Resource
    private OnlineUserService onlineUserService;

    /**
     * 在线用户上传地理位置信息
     * @param systemUserId
     * @param deliveryOrderId
     * @param longitude
     * @param latitude
     * @param time
     */
    @Override
    public void uploadLocation(Long systemUserId, Long deliveryOrderId, String longitude, String latitude, Date time) {
//        根据系统用户id获取在线用户id
       Long onlineUserId = onlineUserService.selectOnlineUserIdBySystemUserId(systemUserId);
//        给当前在线用户添加地理位置信息
       vehicleGpsRecordMapper.uploadLocation(onlineUserId,deliveryOrderId,longitude,latitude,time);
    }

    /**
     * 下载gps位置（查询gps列表）
     * @param deliveryOrderId
     * @return
     */
    @Override
    public List<VehicleGpsRecord> selectVehicleGpsRecordByDeliveryOrderId(Long deliveryOrderId) {

        return vehicleGpsRecordMapper.selectVehicleGpsRecordByDeliveryOrderId(deliveryOrderId);
    }
}
