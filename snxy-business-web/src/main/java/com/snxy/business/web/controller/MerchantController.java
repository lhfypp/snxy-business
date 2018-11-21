package com.snxy.business.web.controller;

import com.snxy.business.service.MerchantService;
import com.snxy.business.service.vo.*;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/merchant")
public class MerchantController {
    @Resource
    private MerchantService merchantService;

    /*
    * 公司负责人完善公司信息
    * */
    @RequestMapping("/company/new")
    public ResultData newCompany(MultipartFile file, NewCompanyVO newCompanyVO, @RequestAttribute("systemUser")SystemUserVO systemUserVO){
        Long companyId = merchantService.newCompany(file,newCompanyVO,systemUserVO);
        log.info("newCompanyVo : [{}]",newCompanyVO);
        return ResultData.success(companyId);
    }

    /*
    * 公司信用代码图片识别
    * */
    @RequestMapping("/company/social/distinguish")
    public ResultData distinguishSocial(MultipartFile file){
        SocialVO socialVO = merchantService.distinguishSocial(file);
        return ResultData.success(socialVO);
    }

    /*
    * 判断公司信息是否已经存在,输入姓名查找公司
    * */
    @RequestMapping("/company/exist")
    public ResultData companyExist(String companyName,Integer showNum){
        List<CompanyVO> companyVOList = merchantService.companyExist(companyName,showNum);
        return ResultData.success(companyVOList);
    }

    /*
    * 如果已经查出公司信息，传入公司id，根据公司id查询公司
    * */
    @RequestMapping("/company/show")
    public ResultData showCompany(Long companyId){
        CompanyVO companyVO = merchantService.showCompany(companyId);
        return ResultData.success(companyVO);
    }

    /*
    * 根据公司id加入公司
    * */
    @RequestMapping("/company/employee/add")
    public ResultData employeeAdd(Long companyId,@RequestAttribute("systemUser") SystemUserVO systemUserVO){
        merchantService.employeeAdd(companyId,systemUserVO.getOnlineUserId());
        return ResultData.success("");
    }





    /*
    * 转移到商户后台管理中   没了
    * */
    /*
    * 添加员工
    * */
    @RequestMapping("/company/employee/new/one")
    public ResultData newOneEmployee( EmployeeVO employeeVO){
        merchantService.newOneEmployee(employeeVO);
        return ResultData.success("");
    }

    /*
    * 批量添加员工
    * */
    @RequestMapping("/company/employee/new/list")
    public ResultData newEmployeeList(@RequestBody List<EmployeeVO> employeeVOList){
        merchantService.newEmployeeList(employeeVOList);
        return ResultData.success("");
    }

    /*
    * 获取所有员工
    * */
    @RequestMapping("/company/employee/all")
    public ResultData showAllEmployee(Long companyId){
        List<EmployeeVO> employeeVOList = merchantService.showAllEmployee(companyId);
        return ResultData.success(employeeVOList);
    }

    /*
     * 负责人变更  批量
     * */
    @RequestMapping(value = "/company/change/principle")
    public ResultData changePrinciple (@RequestBody List<ChangePrincipleVO> changePrincipleVOList){
        merchantService.changePrinciple(changePrincipleVOList);
        return ResultData.success("");
    }

    /*
    * 通讯录是否负责人显示
    * */
    @RequestMapping("/company/show/mail")
    public ResultData showMail(@RequestBody List<MailVO> mailVOList,Long companyId){
        List<MailVO> mailVOS = merchantService.showMail(mailVOList,companyId);

        return ResultData.success(mailVOS);
    }
}
