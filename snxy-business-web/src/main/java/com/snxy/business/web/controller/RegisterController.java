package com.snxy.business.web.controller;

import com.snxy.business.service.IdentityTypeService;
import com.snxy.business.service.vo.IdentityVO;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
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

    /*
     * 注册获取身份列表W
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
    public ResultData chooseIdentity(){
        return ResultData.success("");
    }

}
