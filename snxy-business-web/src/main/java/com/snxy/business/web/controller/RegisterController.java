package com.snxy.business.web.controller;

import com.snxy.business.service.IdentityTypeService;
import com.snxy.business.service.RegisterService;
import com.snxy.business.service.vo.IdentityVO;
import com.snxy.business.service.vo.SystemUserVO;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/register")
public class RegisterController {
    @Resource
    private IdentityTypeService identityTypeService;
    @Resource
    private RegisterService registerService;

    /*
    * 注册登陆密码设置
    * */
    @RequestMapping("/password/new")
    public ResultData newPassword(@RequestAttribute("systemUser")SystemUserVO systemUserVO, String password){
        registerService.newPassword(password,systemUserVO.getSystemUserId());
        return ResultData.success("");
    }
    /**
     * 注册-忘记密码-获取验证码
     * @param mobile
     * @return
     */
    @RequestMapping("/getSmsCode")
    public ResultData getSmsCode(String mobile){
        String smsCode = registerService.getSmsCode(mobile);
        return ResultData.success(smsCode);
    }
    /**
     * 注册-忘记密码-修改密码
     * @param systemUserVO
     * @param mobile
     */
    @RequestMapping("/password/update")
    public void updateRegisterPWD(@RequestAttribute("systemUser")SystemUserVO systemUserVO,String mobile,String smsCode,String newPwd){

        registerService.updateRegisterPWD(systemUserVO.getSystemUserId(),mobile,smsCode,newPwd);
    }
    /*
     * 注册获取身份列表(前台显示列表)W
     * */
    @RequestMapping("/identity/show/list")
    public ResultData showIdentityList(){
        List<IdentityVO> identityVOList = identityTypeService.selectAllIdentity();
        return ResultData.success(identityVOList);
    }

    /*
     * 注册选择身份W
     * */
    @RequestMapping("/identity/choose")
    public ResultData chooseIdentity(@RequestAttribute("systemUser")SystemUserVO systemUserVO, Integer identityId, String name){
        registerService.chooseIdentity(systemUserVO.getSystemUserId(), name, identityId,systemUserVO.getOnlineUserId());
        return ResultData.success("");
    }

}
