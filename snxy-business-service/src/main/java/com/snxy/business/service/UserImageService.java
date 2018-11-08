package com.snxy.business.service;


import org.springframework.web.multipart.MultipartFile;

public interface UserImageService {

    void updateImageById(Long systemUserId, MultipartFile multipartFile  );
}
