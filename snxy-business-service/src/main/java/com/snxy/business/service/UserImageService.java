package com.snxy.business.service;

import com.snxy.business.domain.UserImage;

import java.io.File;

public interface UserImageService {
    void insertSelective(Long userId, File file);
}
