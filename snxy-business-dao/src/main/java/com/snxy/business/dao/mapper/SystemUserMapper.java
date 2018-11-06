package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SystemUser;
<<<<<<< HEAD
=======
import org.apache.ibatis.annotations.Param;

import java.util.Date;
>>>>>>> a779b14a370bb8d9e396182b9f02614933957511

public interface SystemUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

<<<<<<< HEAD
    void updateUserNameById(Long systemUserId, String userName);
=======
    void updateNameByPrimaryKey(@Param("systemUserId") Long systemUserId, @Param("name") String name, @Param("date") Date date);
>>>>>>> a779b14a370bb8d9e396182b9f02614933957511
}