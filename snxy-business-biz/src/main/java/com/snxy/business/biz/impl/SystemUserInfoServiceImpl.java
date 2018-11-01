package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.SystemUserInfoMapper;
import com.snxy.business.domain.SystemUserInfo;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.SystemUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class SystemUserInfoServiceImpl implements SystemUserInfoService {
    @Resource
    private SystemUserInfoMapper systemUserInfoMapper;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private OnlineUserService onlineUserService;
    @Override
    public List<String> searchPhones(String onlineUserID) {
        // 查询出公司id有
        long onlineUserId=1;
        long companyId= companyUserRelationService.selectCompanyId(onlineUserId);
        //查询公司id下的所有用户
        List<Long>onlineUserIds=companyUserRelationService.selectOnlinUserId(companyId);
        //查询出所有的电话
       List<String>phones= onlineUserService.searchphones(onlineUserIds);
        return phones;
    }


}
