package com.snxy.business.web.controller;

import com.snxy.business.service.SystemUserService;
import com.snxy.business.service.UserImageService;
import com.snxy.common.response.ResultData;
import com.snxy.user.agent.service.vo.SystemUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/*
* 基础信息设置
* */
@RestController
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
    * 前端输入验证码，正确则修改成新手机号
    * */
    @RequestMapping("/modifyPhone")
    public ResultData modifyPhone(@RequestAttribute(value = "systemUser",required = false) SystemUserVO systemUserVO, Long userId, String smsCode){
        log.error("获取用户信息:{}",systemUserVO);
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
    * 忘记密码，向用户手机号发送验证码，并把验证码放入redis
    * */
    @RequestMapping("/password/reset")
    public ResultData passwordReset(Long userId){
        systemUserService.passwordReset(userId);
        return ResultData.success("");
    }
    /*
    *验证验证码 不返还报错就是正确？？
    * */
    @RequestMapping("/password/checkSmsCode")
    public ResultData passwordCheckSmsCode(Long userId,String smsCode){
        systemUserService.checkSmsCode(userId,smsCode);
        return ResultData.success("");
    }
    /*
    * 验证码正确，进入下一步，填写新的密码点击保存
    * */
    @RequestMapping("/password/resetNew")
    public ResultData passwordResetNew(Long userId,String newPassword){
        systemUserService.passwordResetNew(userId,newPassword);
        return ResultData.success("");
    }

    /*
    * 修改密码
    * */
    @RequestMapping("/password/modify")
    public ResultData passwordModify(Long userId,String oldPassword,String newPassword){
        systemUserService.passwordModify(userId,oldPassword,newPassword);
        return ResultData.success("");
    }

}
