package com.snxy.business.service;

import com.snxy.business.service.vo.ChangePrincipleVO;
import com.snxy.business.service.vo.EmployeeVO;
import com.snxy.business.service.vo.NewCompanyVO;

import java.util.List;

public interface MerchantService {
    void newCompany(NewCompanyVO newCompanyVO);

    void newOneEmployee(EmployeeVO employeeVO);

    void newEmployeeList(List<EmployeeVO> employeeVOList);

    List<EmployeeVO> showAllEmployee(Long companyId);

    void changePrinciple(List<ChangePrincipleVO> changePrincipleVOList);
}
