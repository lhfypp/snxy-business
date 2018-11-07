package com.snxy.business.service;


import java.io.File;

public interface UserImageService {

    void updateImageById(Long systemUserId, File file);
}
