package com.snxy.business.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 24398 on 2018/9/19.
 */
public interface FileOperationService {
    String upload(MultipartFile file);
    byte[] download(String filePath);

    void delete(String filePath);

}
