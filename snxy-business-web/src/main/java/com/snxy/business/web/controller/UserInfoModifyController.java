package com.snxy.business.web.controller;

import com.snxy.business.service.SystemUserService;
import com.snxy.business.service.UserImageService;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.CheckUtil;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/*
* 基础信息设置
* */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserInfoModifyController {

    @Resource
    private UserImageService userImageService;
    @Resource
    private SystemUserService systemUserService;
    /*
    * 修改/上传头像
    * */
    @RequestMapping("/HeadImage")
    public ResultData HeadImage(Long userId, MultipartFile file){

        return ResultData.success("");
    }

    /*
    * 修改姓名
    * */
    @RequestMapping("/modifyName")
    public ResultData modifyName(String name,Long userId){
        systemUserService.modifyName(name,userId);
        return ResultData.success("");
    }

    /*
    * 修改手机号前发送验证码,把收到的验证码和新手机号都存入缓存中
    * */
    @RequestMapping("/send/smsCode")
    public ResultData smsCode(Long userId,String newMobile){
        systemUserService.smsCode(userId,newMobile);
        return ResultData.success("");
    }

    /*
    * 修改手机验证码
    * */
    @RequestMapping("/modifyPhone")
    public ResultData modifyPhone(Long userId,String smsCode){
        systemUserService.modifyPhone(userId,smsCode);
        return ResultData.success("");
    }

    /*
    *身份证识别
    * */
    @RequestMapping("/identity/check")
    public ResultData identityCheck(String identityNo,Long userId){

        return ResultData.success("");
    }

    /*
    * 身份证验证
    * */
    @RequestMapping("/identity/authorize")
    public ResultData identityAuthorize(Long userId, String identityNo, List<MultipartFile> identityImages){

        return ResultData.success("");
    }

    /*
    * 忘记密码
    * */
    @RequestMapping("/password/reset")
    public ResultData passwordReset(Long userId,String newPassword,String oldPassword){
        systemUserService.passwordReset(userId,newPassword,oldPassword);
        return ResultData.success("");
    }
}
