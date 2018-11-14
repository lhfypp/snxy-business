package com.snxy.business.biz.impl;

import com.snxy.business.biz.config.IdentityTypeEnum;
import com.snxy.business.service.IdentityTypeService;
import com.snxy.business.service.vo.IdentityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IdentityTypeServiceImpl implements IdentityTypeService {

    @Override
    public List<IdentityVO> selectAllIdentity() {
        List<IdentityVO> identityVOList = new ArrayList<>();
        for (IdentityTypeEnum identityTypeEnum : IdentityTypeEnum.values()) {
            if(!identityTypeEnum.getIdentityTypeName().equals("商户负责人")){
                IdentityVO identityVO = IdentityVO.builder()
                                                .id(identityTypeEnum.getIdentityTypeId())
                                                .identityName(identityTypeEnum.getIdentityTypeName())
                                                .build();

                identityVOList.add(identityVO);
            }
        }

        return identityVOList;
    }
}
