package com.snxy.business.dao.mapper;

import com.snxy.business.domain.UserIdentity;
<<<<<<< HEAD
=======
import org.apache.ibatis.annotations.Param;

import java.util.List;
>>>>>>> 0715307c3886f405043faeee6e33e8c66c8ff20e

public interface UserIdentityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserIdentity record);

    int insertSelective(UserIdentity record);

    UserIdentity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserIdentity record);

    int updateByPrimaryKey(UserIdentity record);

    void insertIdentity(UserIdentity userIdentity);

    void insertIdentityList(List<UserIdentity> list);
}