package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.dao.mapper.DirverInfoMapper;
import com.snxy.business.dao.mapper.IdInfoMapper;
import com.snxy.business.domain.*;

import com.snxy.business.service.DirverInfoService;
import com.snxy.business.service.vo.DriverPictureVO;
import com.snxy.business.service.vo.VehicleInfoVO;

import com.snxy.business.service.CompanyUserRelationService;

import com.snxy.business.service.MerchantCompanyService;
import com.snxy.common.exception.BizException;

import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private MerchantCompanyService merchantCompanyService;
    @Resource
    private FileService fileService;

    //保存司机驾驶证  身份证
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveDriverInfo(DriverPictureVO driverPictureVO){
        int j = 0;
        driverPictureVO.setGmtCreate(new Date());
        //图片上传
        List<Image> imageList = new ArrayList<>();
        for (int i = 0; i < driverPictureVO.getFile().size();i++){
            ResultData<String> resultData = fileService.upload(driverPictureVO.getFile().get(i));
            String url = resultData.getData();
            Image image = new Image();
            image.setType(i+1);
            image.setFileaPath(url);
            imageList.add(image);
        }
        //判断图片地址
        for(Image image:imageList){
            if(1==image.getType()){
                driverPictureVO.setIdFrontUrl(image.getFileaPath());
            }else if (2 == image.getType()){
                driverPictureVO.setIdBackUrl(image.getFileaPath());
            }else if (3 == image.getType()){
                driverPictureVO.setDrivingLicenseUrl(image.getFileaPath());
            }
        }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //保存驾驶证
            DirverInfo dirverInfo = new DirverInfo();
            dirverInfo.setOnlineUserId(driverPictureVO.getOnlineUserId());
            dirverInfo.setDrivingLicenseUrl(driverPictureVO.getDrivingLicenseUrl());
            dirverInfo.setGmtCreate(driverPictureVO.getGmtCreate());
            dirverInfo.setDrivingLicenseNumber(driverPictureVO.getDriverLicenseNo());
            j = dirverInfoMapper.insert(dirverInfo);
            //保存身份证
            IdInfo idInfo = new IdInfo();
            idInfo.setName(driverPictureVO.getName());
            idInfo.setOnlineUserId(driverPictureVO.getOnlineUserId());
            idInfo.setIdNumber(driverPictureVO.getIdentityNO());
            idInfo.setIdFrontUrl(driverPictureVO.getIdFrontUrl());
            idInfo.setIdBackUrl(driverPictureVO.getIdBackUrl());
            idInfo.setGmtCreate(driverPictureVO.getGmtCreate());
            try {
                idInfo.setAge(simpleDateFormat.parse(driverPictureVO.getBornDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            idInfo.setGender(driverPictureVO.getGender()=="男"?1:2);
            idInfo.setOnlineUserId(driverPictureVO.getOnlineUserId());
            j = idInfoMapper.insert(idInfo);
            if (j == 0){
                return "保存失败";
            }else {
                return "保存成功";
            }
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

    /**
     * 查询司机所属我的公司信息
     * @param id
     * @return
     */
    @Override
    public List<MerchantCompany> selectDriverOfCompanyById(Long id) {
        //查出在线id
        Long onlineUserId = dirverInfoMapper.selectOnlineUserIdById(id);
        //通过司机onlineUserId查询company_user_relation中onlineUserId
       List<Long> companyIdList = companyUserRelationService.selectCompanyIdByOnlineUserId(onlineUserId);
       //根据companyIdList批量查询公司信息
       List<MerchantCompany> merchantCompanyList =  merchantCompanyService.selectCompanyByCompanyIdList(companyIdList);

        return merchantCompanyList;
    }



}
