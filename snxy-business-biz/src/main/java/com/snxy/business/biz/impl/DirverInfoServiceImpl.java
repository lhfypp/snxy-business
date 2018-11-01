package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.DirverInfoMapper;
import com.snxy.business.dao.mapper.MerchantCompanyMapper;
import com.snxy.business.dao.mapper.VehicleMapper;
import com.snxy.business.domain.*;
import com.snxy.business.service.DirverInfoService;
import com.snxy.business.service.VehicleService;
import com.snxy.business.service.vo.DriverBasicInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DirverInfoServiceImpl implements DirverInfoService {
    @Resource
    private DirverInfoMapper dirverInfoMapper;
    @Resource
    private VehicleService vehicleService;
    @Resource
    private VehicleMapper vehicleMapper;
    @Resource
    private MerchantCompanyMapper merchantCompanyMapper;

    @Override
   public String saveDriverInfo(DriverBasicInfoVo DriverBasicInfo){
        String msg="";
        //从用户信息获取在线用户id
        long onlineUserId=1;
        Date date=new Date();
        DirverInfo dirverInfo=DirverInfo.builder()
                .onlineUserId(onlineUserId)
                .drivingLicenseNumber(DriverBasicInfo.getDrivingLicenseNumber())
                .qualificationCertificateNumber(DriverBasicInfo.getQualificationCertificateNumber())
                .drivingLicenseUrl(DriverBasicInfo.getFile())
                .gmtCreate(date)
                .gmtModified(null)
                .isDelete((byte)0)
                .build();

        int i=dirverInfoMapper.insert(dirverInfo);
        if(1==i){
            msg="司机信息创建成功";
        }else{
            msg="司机信息创建失败";
        }
    return msg;
    }

    @Override
    public DirverInfo searchDriverInfo() {
        //在线用户id直接获取
        long onlineUserId=1;
        return dirverInfoMapper.selectByOnlineUserId(onlineUserId);
    }
   public List<Vehicle> searchVhicles(){
        //获取用户id，查询出司机id
       long onlineUserId=1;
       long id=dirverInfoMapper.selectIdByOnlineUserId(onlineUserId);
        //用driver_info_id查询出所有的车辆信息
       return   vehicleService.searchVehicleList(id);
   }

    //司机车俩信息新建
    @Override
    public void saveDirverVehicle(NewDriverVehicle newDriverVehicle) {
        dirverInfoMapper.insertNewDriverVehicle(newDriverVehicle);
    }

    //车辆信息修改
    @Override
    public void updateDirverVehicle(Long id) {
        vehicleMapper.updateDriverVehicleById(id);
    }

    //司机车辆信息删除
    @Override
    public void deleteDirverVehicle(Long id) {
        vehicleMapper.deleteDirverVehicle(id);
    }

    //司机查看公司信息
    @Override
    public CompanyInfo selectCompanyInfo(long id) {
        MerchantCompany merchantCompany = merchantCompanyMapper.selectCompanyInfoById(id);
        CompanyInfo companyInfo = CompanyInfo.builder()
                .merchantType(merchantCompany.getMerchantType())//公司类别  1.公司  2.个体
                .merchantName(merchantCompany.getMerchantName())//公司名称
                .operationScale(merchantCompany.getOperationScale())//公司规模
                .operationSope(merchantCompany.getOperationSope())//公司经营范围
                .socialInfoCode(merchantCompany.getSocialInfoCode())//公司信用代码
                .corporateCertificationUrl(merchantCompany.getCorporateCertificationUrl())//公司认证图片
                .build();
        return companyInfo;
    }
}
