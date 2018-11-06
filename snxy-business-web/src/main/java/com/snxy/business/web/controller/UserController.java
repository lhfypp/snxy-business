package com.snxy.business.web.controller;


import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.SystemUserService;
import com.snxy.business.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@Resource
@Slf4j
@RequestMapping("/personal")
public class UserController {

     @Resource
     private UserImageService userImageService;
     @Resource
     private OnlineUserService onlineUserService;
     @Resource
     private SystemUserService systemUserService;
    /**
     * 修改个人头像
     * @param systemUserId
     */
     @RequestMapping("/updateImageById")
    public void updateImageById(@RequestParam Long systemUserId, @RequestParam("file") MultipartFile file){

         userImageService.updateImageById(systemUserId, (File) file);
     }
    /**
     * 更换姓名
     * @param systemUserId
     * @param userName
     */
    @RequestMapping("/updateNameById")
    public void updateNameById(@RequestParam Long systemUserId,@RequestParam String userName){
//      在onlineUser表中通过systemId获取onlineId
        Long onlineUserId = onlineUserService.selectOnlineIdBySystemId(systemUserId);
//       更换在线用户姓名
        onlineUserService.updateNameById(onlineUserId,userName);
//        更换系统用户姓名
        systemUserService.updateUserNameById(systemUserId,userName);

    }

}
