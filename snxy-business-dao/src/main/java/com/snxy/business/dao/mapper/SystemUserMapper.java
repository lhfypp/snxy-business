package com.snxy.business.dao.mapper;

import com.snxy.business.domain.SystemUser;

import com.snxy.business.domain.SystemUser;


import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface SystemUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    void updateNameByPrimaryKey(@Param("systemUserId") Long systemUserId, @Param("name") String name, @Param("date") Date date);

    void insertSystemUser(SystemUser systemUser);

    void insertSystemUserList(List<SystemUser> list);

    void updateSystemMobile(@Param("systemUserId") Long systemUserId, @Param("mobile") String newMobile);

    SystemUser selectByPhone(String phone);

    String selectPwdBySystemUserId(Long systemUserId);

    void updatePassword(String password, Long systemUserId);

    void updatePwdBySystemUserId(@Param("systemUserId") Long systemUserId,@Param("newPwd") String newPwd);

    void updatePersonalMobile(@Param("systemUserId") Long systemUserId,@Param("newMobile") String newMobile);

    void updateRegisterPWD(@Param("systemUserId") Long systemUserId, @Param("newPwd") String newPwd);

    List<SystemUser> selectByPhoneList(@Param("phoneList") List<String> phoneList);

    SystemUser selectAccount(Long systemUserId);
}