package com.snxy.business.dao.mapper;

import com.snxy.business.domain.UserImage;

public interface UserImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserImage record);

    int insertSelective(UserImage record);

    UserImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserImage record);

    int updateByPrimaryKey(Long record);

    void updateImageById(Long systemUserId, String url);
}