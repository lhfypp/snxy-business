package com.snxy.business.service;

import com.snxy.business.domain.IdentityType;
import com.snxy.business.service.vo.IdentityVO;

import java.util.List;

public interface IdentityTypeService {
    List<IdentityVO> selectAllIdentity();

    List<IdentityType> listAll(boolean b);

    void insertIdentity(Integer identityTypeId,Long onlineUserId);
}
