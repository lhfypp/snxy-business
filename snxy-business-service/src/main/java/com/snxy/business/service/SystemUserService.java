package com.snxy.business.service;

import java.util.List;

import com.snxy.business.domain.SystemUser;
import org.springframework.web.multipart.MultipartFile;

public interface SystemUserService {
    void modifyName(String name, Long userId);

    void smsCode(Long userId,String newMobile);

    void modifyPhone(Long userId, String smsCode);

    void identityCheck(String identityNo,Long userId);

    void identityAuthorize(Long userId, String identityNo, List<MultipartFile> identityImages);

    void passwordReset(Long userId, String newPassword, String oldPassword);

    SystemUser selectByMobile(String mobile);

    boolean newSystemUser(SystemUser systemUser);

    void changeInitPassword(Long systemUserId, String password);
}
