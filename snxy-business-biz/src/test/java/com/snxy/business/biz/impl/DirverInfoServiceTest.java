package com.snxy.business.biz.impl;

import com.snxy.business.domain.EntranceFeeCapacity;
import com.snxy.business.domain.Image;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.DirverInfoService;
import com.snxy.business.service.EntranceFeeCapacityService;
import com.snxy.business.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DirverInfoServiceTest {

    @Resource
    private DirverInfoService dirverInfoService;
    @Resource
    private EntranceFeeCapacityService entranceFeeCapacityService;
    @Resource
    private VehicleService vehicleService;

  /*  @Test
    public void saveDriverInfoTest(){

        List<Image> imageList = new ArrayList<>();
        Image image = new Image("12311221",1);
        Image image1 = new Image("12311222",2);
        Image image2 = new Image("12311223",3);
        imageList.add(image);
        imageList.add(image1);
        imageList.add(image2);

        DriverPicture driverPicture = new DriverPicture();
        driverPicture.setOnlineUserId(3L);
        driverPicture.setName("ys");
        driverPicture.setIdentityNO("123423424322344");
        driverPicture.setFilePath(imageList);

        this.dirverInfoService.saveDriverInfo(driverPicture);
    }
*/
    @Test
    public void catTypeList(){
        List<EntranceFeeCapacity> entranceFeeCapacityList = this.entranceFeeCapacityService.carTypeList();
        log.info("entranceFeeCapacityList:"+entranceFeeCapacityList.size());
    }

   /* @Test
    public void saveVehicleTest(){
        Vehicle vehicle = new Vehicle();
        vehicle.setOnlineUserId(2L);
        vehicle.setPlateNumber("74174174741");
        vehicle.setVehicleDrivingLicenseFrontUrl("888888888");
        vehicle.setVehicleDrivingLicenseBackUrl("78787878787");
        vehicle.setDriverInfoId(2L);
        vehicle.setEntranceFeeCapacityId(5L);
        this.vehicleService.saveVehicle(vehicle);
    }
*/
    @Test
    public void VehicleListTest(){
        List<Vehicle> vehicleList = vehicleService.vehicleList(1L);
        log.info("vehicleList:"+vehicleList.size());
    }
}
