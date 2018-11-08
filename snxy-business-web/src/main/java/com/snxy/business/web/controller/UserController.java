package com.snxy.business.web.controller;

import com.snxy.business.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserImageService userImageService;

    /**
     * 上传用户头像
     * @param systemUserId
     * @param file
     */
    @RequestMapping("/updateUserImage")
    public void updateUserImage( Long systemUserId , MultipartFile file){
        log.info("============================");
        userImageService.updateImageById(systemUserId,file);
    }
}
