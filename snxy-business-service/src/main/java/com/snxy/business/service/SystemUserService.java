package com.snxy.business.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SystemUserService {
    void modifyName(String name, Long userId);

    void smsCode(Long userId,String newMobile);

    void modifyPhone(Long userId, String smsCode);

    void identityCheck(String identityNo,Long userId);

    void identityAuthorize(Long userId, String identityNo, List<MultipartFile> identityImages);

    void passwordReset(Long userId);

    void checkSmsCode(Long userId, String smsCode);

    void passwordResetNew(Long userId, String newPassword);

    void passwordModify(Long userId, String oldPassword, String newPassword);
}
