package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SystemUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUserInfo record);

    int insertSelective(SystemUserInfo record);

    SystemUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUserInfo record);

    int updateByPrimaryKey(SystemUserInfo record);
    // 查询产地发货单要用到
    List<String> searchPhones(@Param("phone") String phone);
}