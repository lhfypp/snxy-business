package com.snxy.business.web.controller;

import com.snxy.business.service.RegistryService;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.CheckUtil;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    /***
     * 校验输入的验证码
     * @param mobile
     * @param smsCode
     * @return
     */
    @RequestMapping("/user/registry/checkSmsCode")
    public ResultData  checkSMsCode(String mobile,String smsCode){
        CheckUtil.isTrue(StringUtil.isNotBlank(smsCode),"验证码不能为空");
        StringUtil.checkMobile(mobile);
        this.registryService.checkSmsCodeAndRegister(mobile,smsCode);
        return ResultData.success(null);
    }

    /***
     * 修改初始化密码
     * @param systemUserId
     * @param password
     * @return
     */
     @RequestMapping("/user/registry/password")
    public ResultData  changeInitPassword(Long systemUserId,String password){
        CheckUtil.isTrue(StringUtil.isNotBlank(password),"密码不能为空");
        this.registryService.changeInitPassword(systemUserId,password);
        return ResultData.success(null);
    }


    @RequestMapping("/user/registry/identity/save")
    public ResultData  saveIdentity(String name, List<Integer> identities){
        log.info("name : [[]]",name);
        log.info("identities :  [{}]",identities);
        return ResultData.success(null);
    }


}
