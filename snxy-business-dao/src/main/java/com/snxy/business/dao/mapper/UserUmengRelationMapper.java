package com.snxy.business.dao.mapper;

import com.snxy.business.domain.UserUmengRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserUmengRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserUmengRelation record);

    int insertSelective(UserUmengRelation record);

    UserUmengRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserUmengRelation record);

    int updateByPrimaryKey(UserUmengRelation record);

    UserUmengRelation selectBySystemUserId(@Param("systemUserId")Long systemUserId);

    List<UserUmengRelation> selectBySystemUserIdList(@Param("systemUserIdList") List<Long> systemUserIdList);

}