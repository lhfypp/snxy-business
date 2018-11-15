package com.snxy.business.dao.mapper;

import com.snxy.business.domain.DirverInfo;

import com.snxy.business.domain.VhiclePartInfo;

import java.util.List;



public interface DirverInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DirverInfo record);

    int insertSelective(DirverInfo record);

    DirverInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DirverInfo record);

    int updateByPrimaryKey(DirverInfo record);

    List<VhiclePartInfo> searchVhicleInfo(long driverId);



    Long selectOnlineUserIdById(Long id);

}