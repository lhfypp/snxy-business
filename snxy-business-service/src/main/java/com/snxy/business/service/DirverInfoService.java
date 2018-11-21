package com.snxy.business.service;

import com.snxy.business.service.vo.DriverPictureVO;
import com.snxy.business.service.vo.VehicleInfoVO;

import com.snxy.business.domain.MerchantCompany;


import java.util.List;

public interface DirverInfoService {
    String saveDriverInfo(DriverPictureVO driverPictureVO);

    List<VehicleInfoVO> searchVehicleInfo(long driverId);

    List<MerchantCompany> selectDriverOfCompanyById(Long id);


}
