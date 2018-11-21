package com.snxy.business.service;

import com.snxy.business.domain.UserUmengRelation;

import java.util.List;

/**
 * @Author: zhangfeng233
 * @Date: 2018/11/16 14:58
 */
public interface UserUmengRelationService {

    void insertSelective(UserUmengRelation record);

    UserUmengRelation selectBySystemUserId(Long systemUserId);

    List<UserUmengRelation> selectBySystemUserIdList(List<Long> systemUserIdList);
}
