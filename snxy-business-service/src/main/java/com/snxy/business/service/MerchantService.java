package com.snxy.business.service;

import com.snxy.business.service.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MerchantService {
    Long newCompany(MultipartFile file, NewCompanyVO newCompanyVO, SystemUserVO systemUserVO);

    void newOneEmployee(EmployeeVO employeeVO);

    void newEmployeeList(List<EmployeeVO> employeeVOList);

    List<EmployeeVO> showAllEmployee(Long companyId);

    void changePrinciple(List<ChangePrincipleVO> changePrincipleVOList);

    List<MailVO> showMail(List<MailVO> mailVOList,Long companyId);

    List<CompanyVO> companyExist(String companyName,Integer showNum);

    CompanyVO showCompany(Long companyId);

    void employeeAdd(Long companyId, Long onlineUserId);
}
