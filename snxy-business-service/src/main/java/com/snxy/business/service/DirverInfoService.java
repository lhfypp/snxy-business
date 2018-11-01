package com.snxy.business.service;

import com.snxy.business.domain.DirverInfo;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.vo.DriverBasicInfoVo;

import java.util.List;


public interface DirverInfoService {
    String saveDriverInfo(DriverBasicInfoVo DriverBasicInfo);
    DirverInfo searchDriverInfo();
    List<Vehicle> searchVhicles();
}
