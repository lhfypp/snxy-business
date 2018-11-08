package com.snxy.business.biz;

import com.snxy.business.domain.VehicleGpsRecord;
import com.snxy.business.service.VehicleGpsRecordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UploadLocationTests {
    @Resource
    private VehicleGpsRecordService vehicleGpsRecordService;
    /**
     * 上传地理位置信息
     */
    @Test
    public  void uploadLocation1(){
        vehicleGpsRecordService.uploadLocation(1L,2L,"125","36",new Date());
    }
    /**
     * 查询理位置信息列表
     */
    @Test
    public void getGpsList(){
        List<VehicleGpsRecord> vehicleGpsRecordList = vehicleGpsRecordService.selectVehicleGpsRecordByDeliveryOrderId(1L);
        for (int i=0;i<vehicleGpsRecordList.size();i++){
            Long deliveryOrderId = vehicleGpsRecordList.get(i).getDeliveryOrderId();
            Date collectionTime = vehicleGpsRecordList.get(i).getCollectionTime();
            Float latitude = vehicleGpsRecordList.get(i).getLatitude();
            Float longitude = vehicleGpsRecordList.get(i).getLongitude();
            Long onlineUserId = vehicleGpsRecordList.get(i).getOnlineUserId();
            System.out.print("======>>>"+deliveryOrderId+"<==>"+collectionTime+"<==>"+latitude+"<==>"+longitude+"<==>"+onlineUserId);
        }
    }
}
