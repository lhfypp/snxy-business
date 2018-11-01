package com.snxy.business.web.controller;

import com.snxy.business.domain.IdentityType;
import com.snxy.business.service.RegistryService;
import com.snxy.business.service.vo.LoginUserVO;
import com.snxy.business.service.vo.SystemUserVo;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.CheckUtil;
import com.snxy.common.util.StringUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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
        CheckUtil.isTrue(StringUtil.isNotBlank(mobile),"手机号码不能为空");
        if(!StringUtil.checkMobile(mobile)){
            throw new BizException("手机号码格式不正确");
        };
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
        CheckUtil.isTrue(StringUtil.isNotBlank(mobile),"手机号不能为空");
        if(!StringUtil.checkMobile(mobile)){
           throw new BizException("手机号码格式不正确");
        };
        this.registryService.checkSmsCodeAndRegister(mobile,smsCode);
        return ResultData.success(null);
    }


    @RequestMapping("/user/registry/getToken")
    public ResultData<SystemUserVo> getToken(@RequestBody LoginUserVO loginUserVO){
        CheckUtil.isTrue(loginUserVO.getDeviceType() != null, "登陆用户设备参数不能为空");
        loginUserVO.checkParam();
        SystemUserVo systemUserVO = this.registryService.getToken(loginUserVO);
        return ResultData.success(systemUserVO);
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

    /***
     * 查询所有的身份列表
     * @return
     */
    @RequestMapping("/user/registry/identity/list")
    public ResultData<List<IdentityType>> listIdentity(){
        List<IdentityType> userIdentities = this.registryService.listIdentity();
        return ResultData.success(userIdentities);
    }

    /***
     * 保存身份信息
     * @param onlineUserId
     * @param name
     * @param identityArr
     * @return
     */
    @RequestMapping("/user/registry/identity/save")
    public ResultData  saveIdentity(Long onlineUserId,String name, Integer[] identityArr){
         CheckUtil.isTrue(StringUtil.isNotBlank(name),"输入姓名不能为空");
         if( identityArr == null || identityArr.length == 0 ){
             throw new BizException("请选择身份类型");
         }

         this.registryService.saveIdentityAndName(onlineUserId,name, Arrays.asList(identityArr));
        return ResultData.success(null);
    }


}
