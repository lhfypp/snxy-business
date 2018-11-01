package com.snxy.business.dao.mapper;

import com.snxy.business.domain.DirverInfo;
import org.apache.ibatis.annotations.Param;

public interface DirverInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DirverInfo record);

    int insertSelective(DirverInfo record);

    DirverInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DirverInfo record);

    int updateByPrimaryKey(DirverInfo record);
    DirverInfo selectByOnlineUserId(@Param("onlineUserId") Long onlineUserId);
    long selectIdByOnlineUserId(@Param("OnlineUseId")long onlineUserId);
}