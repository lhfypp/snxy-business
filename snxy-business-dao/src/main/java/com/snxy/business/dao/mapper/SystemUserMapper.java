package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SystemUser;
import org.apache.ibatis.annotations.Param;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    String selectMobileById(Long userId);

    SystemUser selectByMobile(@Param("mobile") String mobile, @Param("isDelete") Byte isDelete);
}