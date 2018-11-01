package com.snxy.business.web.controller;

import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.CompanyVegetableService;
import com.snxy.business.service.MerchantCompanyService;
import com.snxy.business.service.vo.*;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
*商户信息设置
**/

@RestController
@Slf4j
@RequestMapping("/vender")
public class VenderUserController {

    @Resource
    private MerchantCompanyService merchantCompanyService;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private CompanyVegetableService companyVegetableService;

    /*
    * 我的公司
    * */
    @RequestMapping(value = "/company/basicInfo")
    public ResultData basicInfo (Long onlineUserId){
        List<MerchantCompanyVo> merchantCompanyVoList = merchantCompanyService.selectByPrimaryKey(onlineUserId);

        return ResultData.success(merchantCompanyVoList);
    }

    /*
    * 创建新公司
    * */
    @RequestMapping(value = "/company/new")
    public ResultData createNewCompany (@RequestBody MerchantCompanyVo merchantCompanyVo){
        merchantCompanyService.createNewCompany(merchantCompanyVo);
        return ResultData.success("");
    }

    /*
    * 负责人变更
    * */
    @RequestMapping(value = "/company/changePrinciple")
    public ResultData changePrinciple (@RequestBody ChangePrincipleVo changePrincipleVo){
        companyUserRelationService.changePrinciple(changePrincipleVo);

        return ResultData.success("");
    }

    /*
    * 查看商户经营品类
    * */
    @RequestMapping(value = "/company/goods/list")
    public ResultData showCompanyGoodsList(Long companyId){
        List<GoodsListVo> goodsListVoList = companyVegetableService.showGoodsList(companyId);
        return ResultData.success(goodsListVoList);
    }

    /*
    * 添加商品经营品类
    * */
    @RequestMapping(value = "/company/goods/add")
    public ResultData addCompanyGoods(@RequestBody AddCompanyGoodsVo addCompanyGoodsVo){
        companyVegetableService.addCompanyGoods(addCompanyGoodsVo);
        return ResultData.success("");
    }

    /*
    * 删除商品经营品类
    * */
    @RequestMapping(value = "/company/goods/delete")
    public ResultData deleteCompanyGoods(@RequestBody AddCompanyGoodsVo addCompanyGoodsVo){
        companyVegetableService.deleteCompanyGoods(addCompanyGoodsVo);
        return ResultData.success("");
    }

    /*
    * 公司信息修改
    * */
    @RequestMapping(value = "/company/modify")
    public ResultData modifyCompanyMessage(@RequestBody ModifyCompanyMessageVo modifyCompanyMessageVo){
        merchantCompanyService.modifyCompanyMessage(modifyCompanyMessageVo);
        return ResultData.success("");
    }

    /*
    * 员工信息列表
    * */
    @RequestMapping(value = "/employee/list")
    public ResultData showEmployeeList(Long companyId){
        List<CompanyUserListVo> companyUserListVos = companyUserRelationService.showEmployeeList(companyId);
        return ResultData.success(companyUserListVos);
    }

    /*
    * 员工信息新增
    * */
    @RequestMapping(value = "/employee/new")
    public ResultData newEmployee(@RequestBody CompanyUserListVo companyUserListVo){
        companyUserRelationService.newEmployee(companyUserListVo);
        return ResultData.success("");
    }

    /*
    * 员工信息修改
    * */
    @RequestMapping(value = "/employee/modify")
    public ResultData modifyEmployee(@RequestBody CompanyUserListVo companyUserListVo){
        companyUserRelationService.updateCompanyUser(companyUserListVo);
        return ResultData.success("");
    }

    /*
    * 员工信息删除
    * */
    @RequestMapping(value = "/employee/delete")
    public ResultData deleteEmployee(Long onlineUserId){
        companyUserRelationService.deleteEmployee(onlineUserId);
        return ResultData.success("");
    }

    /*
    * 我的银行卡(暂过)
    * */
    @RequestMapping(value = "/card/list")
    public ResultData showCardList(){


        return ResultData.success("");
    }
}
