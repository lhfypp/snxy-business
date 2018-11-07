package com.snxy.business.web.controller;

import com.snxy.business.service.MerchantService;
import com.snxy.business.service.vo.ChangePrincipleVO;
import com.snxy.business.service.vo.EmployeeVO;
import com.snxy.business.service.vo.NewCompanyVO;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultData newCompany(@RequestBody NewCompanyVO newCompanyVO){
        merchantService.newCompany(newCompanyVO);
        return ResultData.success("");
    }

    /*
    * 添加员工
    * */
    @RequestMapping("/company/employee/new/one")
    public ResultData newOneEmployee(@RequestBody EmployeeVO employeeVO){
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
}
