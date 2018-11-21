package com.snxy.business.service;

import com.snxy.business.service.vo.DriverLicenseVO;
import com.snxy.business.service.vo.IdCardInfoVO;
import com.snxy.business.service.vo.VehicleLicenseVO;
import org.springframework.web.multipart.MultipartFile;

public interface DiscernFileSerice {
    IdCardInfoVO idcardFront(MultipartFile file);

    IdCardInfoVO idcardBack(MultipartFile file);

    DriverLicenseVO drivingFront(MultipartFile file);

    VehicleLicenseVO vehicFront(MultipartFile file);
}
