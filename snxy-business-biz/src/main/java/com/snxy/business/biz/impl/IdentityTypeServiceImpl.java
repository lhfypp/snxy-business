package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.IdentityTypeMapper;
import com.snxy.business.domain.IdentityType;
import com.snxy.business.service.IdentityTypeService;
import com.snxy.business.service.vo.IdentityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IdentityTypeServiceImpl implements IdentityTypeService {

    @Resource
    private IdentityTypeMapper identityTypeMapper;

    @Override
    public List<IdentityVO> selectAllIdentity() {
        List<IdentityType> identityTypeList = identityTypeMapper.selectAllType();
        List<IdentityVO> identityVOList = new ArrayList<>();
        for (int i = 0; i < identityTypeList.size(); i++) {
            IdentityVO identityVO = new IdentityVO();
            BeanUtils.copyProperties(identityTypeList.get(i),identityVO);
            identityVOList.add(identityVO);
        }
        return identityVOList;
    }
}
