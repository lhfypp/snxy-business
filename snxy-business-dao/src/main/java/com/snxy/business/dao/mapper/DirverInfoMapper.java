package com.snxy.business.dao.mapper;

import com.snxy.business.domain.DirverInfo;
import com.snxy.business.domain.MerchantCompany;

public interface DirverInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DirverInfo record);

    int insertSelective(DirverInfo record);

    DirverInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DirverInfo record);

    int updateByPrimaryKey(DirverInfo record);

    Long selectOnlineUserIdById(Long id);
}