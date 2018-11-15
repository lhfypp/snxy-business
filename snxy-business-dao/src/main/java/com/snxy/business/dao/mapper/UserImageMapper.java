package com.snxy.business.dao.mapper;

import com.snxy.business.domain.UserImage;
import org.apache.ibatis.annotations.Param;

public interface UserImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserImage record);

    int insertSelective(UserImage record);

    UserImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserImage record);

    int updateByPrimaryKey(Long record);

    void updateImageById(@Param("systemUserId") Long systemUserId, @Param("url")String url);

    String selectImageBySystemUserId(Long systemUserId);
}