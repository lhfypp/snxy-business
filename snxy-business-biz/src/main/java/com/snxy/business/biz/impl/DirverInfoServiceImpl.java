package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.DirverInfoMapper;
import com.snxy.business.dao.mapper.IdInfoMapper;
import com.snxy.business.domain.*;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.DirverInfoService;
import com.snxy.business.service.MerchantCompanyService;
import com.snxy.common.exception.BizException;
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
    private IdInfoMapper idInfoMapper;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private MerchantCompanyService merchantCompanyService;

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
        String idNumber = idInfoMapper.selectByidNumber(driverPicture.getIdentityNO());
        if (!idNumber.equals(driverPicture.getIdentityNO())){
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
        }else {
            throw new BizException("您已注册，请登录");
        }
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
