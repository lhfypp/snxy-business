package com.snxy.business.service;

import com.snxy.business.domain.DriverPicture;
import com.snxy.business.domain.MerchantCompany;

import java.util.List;

public interface DirverInfoService {
    void saveDriverInfo(DriverPicture driverPicture);

    List<MerchantCompany> selectDriverOfCompanyById(Long id);


}
