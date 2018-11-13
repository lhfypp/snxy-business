package com.snxy.business.service;

import org.springframework.web.multipart.MultipartFile;

public interface VersionService {
    void release(Long systemUserId,String versionNum, MultipartFile file);

    String selectUrlBySystemUserId(Long systemUserId);
}
