package com.snxy.business.web.controller;



import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.PerSettingsHomepageService;
import com.snxy.business.service.vo.CompanyVO;
import com.snxy.business.service.vo.SystemUserVO;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

/**
 * 个人中心
 * @author
 *
 */
@RestController
@Slf4j
@RequestMapping("/person")
public class PersonalCenterController {
    @Resource
    private PerSettingsHomepageService PerSettingsHomepageService;
    @Resource
    private OnlineUserService onlineUserService;
    //设置所属商户(搜索)
    @RequestMapping("/company/search")
    public ResultData setTheownerMerchant( String companyName){
        List<CompanyVO> CompanyVO=PerSettingsHomepageService.searchCompanyVO(companyName);

        return ResultData.success(CompanyVO);
    }
    //选择加入商户
    @RequestMapping("/company/save")
   public ResultData saveJoinTheMerchants(@RequestAttribute(value = "systemUser",required = false) SystemUserVO systemUserVO, long companyId){
       long userId=onlineUserService.searchOnlineUserId(systemUserVO.getSystemUserId().toString()) ;//查询出在线id

     return ResultData.success(PerSettingsHomepageService.saveJoinTheMerchants(userId,companyId));
   }
    //通知商户模板信息下载
    @RequestMapping("/company/Notify/download")
    public ResultData notifyDownLoadMerchantTemplate(){
     String name=null;
      //查询出姓名
        //查询出商户负责人，通过server层传msg
        return ResultData.success("请下载进出门app");
    }
    //预约办卡
    @RequestMapping("Reserve/card/handle")
    public ResultData handleCardReserve(){
        log.info("通知中信预约办卡");
        return null;
    }
}
