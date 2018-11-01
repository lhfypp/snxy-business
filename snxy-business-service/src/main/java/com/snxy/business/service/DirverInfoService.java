package com.snxy.business.service;

import com.snxy.business.domain.CompanyInfo;
import com.snxy.business.domain.DirverInfo;
import com.snxy.business.domain.NewDriverVehicle;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.vo.DriverBasicInfoVo;

import java.util.List;


public interface DirverInfoService {
    String saveDriverInfo(DriverBasicInfoVo DriverBasicInfo);

    DirverInfo searchDriverInfo();

    List<Vehicle> searchVhicles();

    void saveDirverVehicle(NewDriverVehicle newDriverVehicle);

    void updateDirverVehicle(Long id);

    void deleteDirverVehicle(Long id);

    CompanyInfo selectCompanyInfo(long id);
}
