package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.IdentityTypeMapper;
import com.snxy.business.domain.IdentityType;
import com.snxy.business.domain.UserIdentity;
import com.snxy.business.service.IdentityTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class IdentityTypeServiceImpl implements IdentityTypeService {

    @Resource
    private IdentityTypeMapper identityTypeMapper;

    @Override
    public List<IdentityType> listAll(boolean excludeDeleted) {
        byte isDelete = 0;
        if( excludeDeleted ){
            isDelete = 1;
        }
        List<IdentityType> identityTypes =  identityTypeMapper.listAll(isDelete);
        return identityTypes;
    }

    @Override
    public List<IdentityType> selectAllType() {
        List<IdentityType> identityTypeList = identityTypeMapper.selectAllType();
        return identityTypeList;
    }
}
