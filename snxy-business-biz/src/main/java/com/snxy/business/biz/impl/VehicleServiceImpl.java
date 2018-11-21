package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.dao.mapper.VehicleMapper;
import com.snxy.business.domain.EntranceFeeCapacity;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.EntranceFeeCapacityService;
import com.snxy.business.service.VehicleService;
import com.snxy.business.service.vo.CarVO;
import com.snxy.business.service.vo.VehicleVO;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleMapper vehicleMapper;
    @Resource
    private EntranceFeeCapacityService entranceFeeCapacityService;
    @Resource
    private FileService fileService;

    @Override
    public List<CarVO> selectByDriverId(Long driverOnlineUserId) {
        List<Vehicle> vehicleList = vehicleMapper.selectByDriverId(driverOnlineUserId);
        List<EntranceFeeCapacity> entranceFeeCapacities = entranceFeeCapacityService.selectAll();
        Map<Long, String> idTypeMap = entranceFeeCapacities.parallelStream().collect(Collectors.toMap(EntranceFeeCapacity::getId, EntranceFeeCapacity::getCarryingCapacity));
        List<CarVO> carVOList = vehicleList.parallelStream().map(s -> CarVO.builder()
                .carId(s.getId())
                .carryingCapacity(idTypeMap.get(s.getEntranceFeeCapacityId()))
                .carPlateNo(s.getPlateNumber())
                .build())
                .collect(Collectors.toList());
        return carVOList;
    }

    //保存行驶证
    @Override
    public String saveVehicle(VehicleVO vehicleVO) {
        //上传图片
        ResultData<String> resultData = fileService.upload(vehicleVO.getFile());
        if (!resultData.isResult()) {
            throw new BizException("上传失败，请重试");
        }
        String url = resultData.getData();

        Vehicle vehicle = Vehicle.builder()
                .driverInfoId(vehicleVO.getDriverInfoId())
                .vehicleDrivingLicenseNumber(vehicleVO.getVehicleDrivingLicenseNumber())
                .vehicleDrivingLicenseUrl(url)
                .entranceFeeCapacityId(vehicleVO.getEntranceFeeCapacityId())
                .plateNumber(vehicleVO.getPlateNumber())
                .tonnage(vehicleVO.getTonnage())
                .gmtCreate(new Date())
                .build();
        int result = vehicleMapper.insert(vehicle);
        if (result != 1) {
            throw new BizException("保存失败，请重新再试");
        }
        return "保存成功";
    }

    //行驶证列表展示
    @Override
    public List<Vehicle> vehicleList(Long dirverInfoId) {
        return vehicleMapper.selectByKey(dirverInfoId);
    }

    @Override
    public Vehicle selectTonnageByDriverNum(String driverPlateNumber) {
        if (driverPlateNumber == null) {
            throw new BizException("车牌号不能为空");
        }
        Vehicle vehicle = vehicleMapper.selectTonnageByDriverNum(driverPlateNumber);
        return vehicle;
    }

    public Vehicle selectByCarNo(String driverPlateNumber) {
        Vehicle vehicle = vehicleMapper.selectByCarNo(driverPlateNumber);
        return vehicle;
    }
}
