package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    void updateNameByPrimaryKey(@Param("systemUserId") Long systemUserId, @Param("name") String name, @Param("date") Date date);
}