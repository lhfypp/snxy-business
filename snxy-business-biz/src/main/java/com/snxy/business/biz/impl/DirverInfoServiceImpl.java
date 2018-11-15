package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.DirverInfoMapper;
import com.snxy.business.dao.mapper.IdInfoMapper;
import com.snxy.business.domain.*;
import com.snxy.business.service.DirverInfoService;
import com.snxy.business.service.vo.VehicleInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DirverInfoServiceImpl implements DirverInfoService {

    @Resource
    private DirverInfoMapper dirverInfoMapper;
    @Resource
    private IdInfoMapper idInfoMapper;

    //保存司机驾驶证  身份证
    @Override
    public void saveDriverInfo(DriverPicture driverPicture) {
        driverPicture.setGmtCreate(new Date());
        //判断图片地址
        List<Image> list = driverPicture.getFilePath();
        for(Image image:list){
            if(1==image.getType()){
                driverPicture.setIdFrontUrl(image.getFileaPath());
            }else if (2 == image.getType()){
                driverPicture.setIdBackUrl(image.getFileaPath());
            }else if (3 == image.getType()){
                driverPicture.setDrivingLicenseUrl(image.getFileaPath());
            }
        }
        //保存驾驶证
        DirverInfo dirverInfo = new DirverInfo();
        dirverInfo.setOnlineUserId(driverPicture.getOnlineUserId());
        dirverInfo.setDrivingLicenseUrl(driverPicture.getDrivingLicenseUrl());
        dirverInfo.setGmtCreate(driverPicture.getGmtCreate());
        dirverInfoMapper.insert(dirverInfo);
        //保存身份证
        IdInfo idInfo = new IdInfo();
        idInfo.setName(driverPicture.getName());
        idInfo.setOnlineUserId(driverPicture.getOnlineUserId());
        idInfo.setIdNumber(driverPicture.getIdentityNO());
        idInfo.setIdFrontUrl(driverPicture.getIdFrontUrl());
        idInfo.setIdBackUrl(driverPicture.getIdBackUrl());
        idInfo.setGmtCreate(driverPicture.getGmtCreate());
        idInfoMapper.insert(idInfo);
    }

    @Override
    public List<VehicleInfoVO> searchVehicleInfo(long driverId) {
        List<VehicleInfoVO> vehicleInfoVOList=new ArrayList<>();
        //查询出司机对应的车辆信息
        List<VhiclePartInfo> VhiclePartInfoList=dirverInfoMapper.searchVhicleInfo(driverId);
        VhiclePartInfoList.forEach((vhiclePartInfo)->vehicleInfoVOList.add(VehicleInfoVO.builder()
                .vehicleId(vhiclePartInfo.getVehicleId())
                .carPlateNO(vhiclePartInfo.getCarPlateNO())
                .build()));
        return vehicleInfoVOList;
    }
}
