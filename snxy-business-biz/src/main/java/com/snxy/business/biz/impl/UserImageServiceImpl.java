package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.UserImageMapper;
import com.snxy.business.domain.UserImage;
import com.snxy.business.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Service
@Slf4j
public class UserImageServiceImpl implements UserImageService {
    @Resource
    private UserImageMapper userImageMapper;

    /*
    *
    * */
    @Override
    public void insertSelective(Long userId, File file) {
        UserImage userImage = new UserImage();
        userImage.setSystemUserId(userId);
        userImage.setUrl(file.toString());


    }
}
