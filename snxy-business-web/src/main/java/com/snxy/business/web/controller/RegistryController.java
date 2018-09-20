package com.snxy.business.web.controller;

import com.snxy.business.service.RegistryService;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.CheckUtil;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by 24398 on 2018/9/19.
 */

@RestController
@Slf4j
public class RegistryController {

    @Resource
    private RegistryService registryService;


    /***
     * 注册获取短信验证码
     * @param mobile
     */
    @RequestMapping("/user/registry/smsCode")
    public void registrySmsCode(String mobile){
        this.registryService.getRegistrySmsCode(mobile);
    }


    @RequestMapping("/user/registry/checkSmsCode")
    public ResultData  checkSMsCode(String mobile,String smsCode){
        CheckUtil.isTrue(StringUtil.isNotBlank(smsCode),"验证码不能为空");
        StringUtil.checkMobile(mobile);
        this.registryService.checkSmsCode(mobile,smsCode);
        return null;
    }



}
