package com.snxy.business.biz.impl;

import com.snxy.business.biz.config.IdentityTypeEnum;
import com.snxy.business.domain.IdentityType;
import com.snxy.business.service.IdentityTypeService;
import com.snxy.business.service.UserIdentityService;
import com.snxy.business.service.vo.IdentityVO;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IdentityTypeServiceImpl implements IdentityTypeService {
    @Resource
    private UserIdentityService userIdentityService;

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

    @Override
    public List<IdentityType> listAll(boolean b) {
        return null;
    }

    @Override
    public void insertIdentity(Integer identityTypeId,Long onlineUserId) {
        //先查询出当前用户的身份,有可能当前用户有多个身份
        List<Integer> identityIdList = userIdentityService.selectIdentityIdByOnlineUserId(onlineUserId);
        //判断用户要添加的身份和当前用户身份是否一样，一样则不能添加
        for (int i =0;i<identityIdList.size();i++){
            if (identityTypeId==identityIdList.get(i)){
                throw new BizException("不能重复添加一样的身份");
            }
        }
        userIdentityService.insertIdentityByOnlineUserId(identityTypeId,onlineUserId);
    }
}
