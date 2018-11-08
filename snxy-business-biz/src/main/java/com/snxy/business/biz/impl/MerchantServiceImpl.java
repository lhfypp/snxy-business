package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.domain.*;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.ChangePrincipleVO;
import com.snxy.business.service.vo.EmployeeVO;
import com.snxy.business.service.vo.NewCompanyVO;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.MD5Util;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    @Resource
    private MerchantCompanyService merchantCompanyService;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private UserIdentityService userIdentityService;
    @Resource
    private FileService fileService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newCompany(NewCompanyVO newCompanyVO) {
        //加上判断是否填写信息===姓名不能为空
        if(StringUtil.isBlank(newCompanyVO.getMerchantName())){
            throw new BizException("公司名称为空");
        }
        //判断公司名为全中文
        if(!StringUtil.isChinese(newCompanyVO.getMerchantName())){
            throw new BizException("请输入中文公司名");
        }
        //判断公司名是否重复
        List<String> nameList = merchantCompanyService.selectAllName();
        for (int i = 0; i < nameList.size(); i++) {
            if(newCompanyVO.equals(nameList.get(i))){
                throw new BizException("用户名已存在");
            }
        }
        //判断公司名长度
        if(newCompanyVO.getMerchantName().length()>20){
            throw new BizException("用户名长度应为20位以内");
        }

        ResultData<String> upload = fileService.upload(newCompanyVO.getFile());
        if (!upload.isResult()) {
            throw new BizException(upload.getMsg());
        }
        String url = upload.getData();
        MerchantCompany merchantCompany = MerchantCompany.builder()
                .certificationStatus((byte)0)
                .gmtCreate(new Date())
                .corporateCertificationUrl(url)
                .build();
        BeanUtils.copyProperties(newCompanyVO,merchantCompany);
        merchantCompanyService.insertCompanyMessage(merchantCompany);

        CompanyUserRelation companyUserRelation = CompanyUserRelation.builder()
                .companyId(merchantCompany.getId())
                .onlineUserId(newCompanyVO.getOnlineUserId())
                .gmtCreate(new Date())
                .isResponsible(0)
                .build();
        companyUserRelationService.insertCompanyUserRelation(companyUserRelation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newOneEmployee(EmployeeVO employeeVO) {
        OnlineUser onlineUser = onlineUserService.selectByPhone(employeeVO.getPhone());
        if(onlineUser==null){
            SystemUser systemUser = SystemUser.builder()
                    .account("系统生成账号")
                    .chineseName(employeeVO.getUserName())
                    .mobile(employeeVO.getPhone())
                    .regDate(new Date())
                    .pwd(MD5Util.encrypt("111111"))
                    .accountStatus((byte)0)
                    .gmtCreate(new Date())
                    .build();
            systemUserService.insertSystemUser(systemUser);

            OnlineUser onlineUser1 = OnlineUser.builder()
                    .userName(employeeVO.getUserName())
                    .phone(employeeVO.getPhone())
                    .systemUserId(systemUser.getId())
                    .build();
            onlineUserService.insertOnlineUser(onlineUser1);

            saveCompanyUserIdentity(onlineUser1.getId(),employeeVO.getCompanyId());

            //这种没有注册的情况下，调用短信通知接口，通知被添加的用户进行APP下载（预留）



        }else {
            CompanyUserRelation companyUserRelation = companyUserRelationService.selectCompanyUserRelation(onlineUser.getId());
            if(companyUserRelation!=null){
                Long oldCompanyId = companyUserRelation.getCompanyId();
                Long newCompanyId = employeeVO.getCompanyId();
                if(oldCompanyId.equals(newCompanyId)){
                    throw new BizException("您已添加该员工");
                }else {
                    throw new BizException("该员工已被其他公司添加，请确认核实");
                }
            }else {
                saveCompanyUserIdentity(onlineUser.getId(),employeeVO.getCompanyId());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newEmployeeList(List<EmployeeVO> employeeVOList) {
        Long companyId = employeeVOList.get(0).getCompanyId();
        List<String> phoneList = employeeVOList.parallelStream().map(EmployeeVO::getPhone).collect(Collectors.toList());
        //已注册用户集合
        List<OnlineUser> onlineUserList = onlineUserService.selectByPhoneList(phoneList);

        if(onlineUserList!=null){
            /// 待添加UserIdentity
            List<UserIdentity> userIdentities = onlineUserList.parallelStream().map(onlineUser -> UserIdentity.builder()
                    .identityId(2)
                    .onlineUserId(onlineUser.getId())
                    .build())
                    .collect(Collectors.toList());
            userIdentityService.insertIdentityList(userIdentities);

            // 待添加CompanyUserRelation
            List<CompanyUserRelation> companyUserRelationList = onlineUserList.parallelStream().map(onlineUser -> CompanyUserRelation.builder()
                    .onlineUserId(onlineUser.getId())
                    .isResponsible(0)
                    .companyId(companyId)
                    .gmtCreate(new Date())
                    .build())
                    .collect(Collectors.toList());
            companyUserRelationService.insertCompanyRelationList(companyUserRelationList);
        }
        List<String> signPhones = onlineUserList.parallelStream().map(OnlineUser::getPhone).collect(Collectors.toList());


        //未注册用户集合
        phoneList.removeAll(signPhones);
        if(phoneList!=null){
            Map<String,String> phoneNameMap = employeeVOList.parallelStream().collect(Collectors.toMap(EmployeeVO::getPhone,EmployeeVO::getUserName));

            List<SystemUser> systemUserList = phoneList.parallelStream().map(s -> SystemUser.builder()
                    .account(s)
                    .accountStatus((byte)0)
                    .chineseName(phoneNameMap.get(s))
                    .mobile(s)
                    .pwd(MD5Util.encrypt("111111"))
                    .regDate(new Date())
                    .gmtCreate(new Date())
                    .build()  )
                    .collect(Collectors.toList());
            systemUserService.insertSystemUserList(systemUserList);
            System.out.println(systemUserList);

            Map<String,Long> phoneSystemUserIdMap = systemUserList.parallelStream().collect(Collectors.toMap(SystemUser::getMobile,SystemUser::getId));
            List<OnlineUser> onlineUserList1 = phoneList.parallelStream().map(s -> OnlineUser.builder()
                    .systemUserId(phoneSystemUserIdMap.get(s))
                    .phone(s)
                    .userName(phoneNameMap.get(s))
                    .build())
                    .collect(Collectors.toList());
            onlineUserService.insertOnlineUserList(onlineUserList1);

            Map<String,Long> phoneOnlineUserIdMap = onlineUserList1.parallelStream().collect(Collectors.toMap(OnlineUser::getPhone,OnlineUser::getId));
            List<UserIdentity> userIdentities1 = phoneList.parallelStream().map(s -> UserIdentity.builder()
                    .onlineUserId(phoneOnlineUserIdMap.get(s))
                    .identityId(2)
                    .build())
                    .collect(Collectors.toList());
            userIdentityService.insertIdentityList(userIdentities1);

            List<CompanyUserRelation> companyUserRelations = phoneList.parallelStream().map(s -> CompanyUserRelation.builder()
                    .companyId(companyId)
                    .gmtCreate(new Date())
                    .isResponsible(0)
                    .onlineUserId(phoneOnlineUserIdMap.get(s))
                    .build())
                    .collect(Collectors.toList());
            companyUserRelationService.insertCompanyRelationList(companyUserRelations);

            //这种没有注册的情况下，调用短信通知接口，通知被添加的用户进行APP下载（预留）



        }
    }

    @Override
    public List<EmployeeVO> showAllEmployee(Long companyId) {
        List<CompanyUserRelation> companyUserRelationList = companyUserRelationService.selectAllByCompanyId(companyId);
        List<Long> onlineUserIdList = companyUserRelationList.parallelStream().map(companyUserRelation -> companyUserRelation.getOnlineUserId()).collect(Collectors.toList());


        List<OnlineUser> onlineUserList = onlineUserService.selectByOnlineUserIdList(onlineUserIdList);
        Map<Long,String> phoneOnlineUserIdMap = onlineUserList.parallelStream().collect(Collectors.toMap(OnlineUser::getId,OnlineUser::getPhone));
        Map<Long,String> userNameOnlineUserIdMap = onlineUserList.parallelStream().collect(Collectors.toMap(OnlineUser::getId,OnlineUser::getUserName));
        Map<Long,Integer> isResponsibleOnlineUserIdMap = companyUserRelationList.parallelStream().collect(Collectors.toMap(CompanyUserRelation::getOnlineUserId,CompanyUserRelation::getIsResponsible));
        List<EmployeeVO> employeeVOList = onlineUserIdList.parallelStream().map(s -> EmployeeVO.builder()
                  .phone(phoneOnlineUserIdMap.get(s))
                  .userName(userNameOnlineUserIdMap.get(s))
                  .beResponsible(isResponsibleOnlineUserIdMap.get(s).equals(0) ? false : true)
                  .build())
                  .collect(Collectors.toList());
        return employeeVOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePrinciple(List<ChangePrincipleVO> changePrincipleVOList) {
        List<String> phoneList = changePrincipleVOList.parallelStream().map(changePrincipleVO -> changePrincipleVO.getPhone()).collect(Collectors.toList());
        //有onlineUserId和phone    参数中有phone和isResponsible
        List<OnlineUser> onlineUserList = onlineUserService.selectByPhoneList(phoneList);
        Map<Long,String> onlineUserIdPhoneMap = onlineUserList.parallelStream().collect(Collectors.toMap(OnlineUser::getId,OnlineUser::getPhone));
        Map<String,Integer> phoneIsResponsible = changePrincipleVOList.parallelStream().collect(Collectors.toMap(ChangePrincipleVO::getPhone,ChangePrincipleVO::getIsResponsible));

        List<Long> onlineUserIdList = onlineUserList.parallelStream().map(onlineUser -> onlineUser.getId()).collect(Collectors.toList());
        List<CompanyUserRelation> companyUserRelationList = onlineUserIdList.parallelStream().map(s -> CompanyUserRelation.builder()
                     .onlineUserId(s)
                     .isResponsible(phoneIsResponsible.get(onlineUserIdPhoneMap.get(s)))
                     .gmtModifed(new Date())
                     .build())
                     .collect(Collectors.toList());

            companyUserRelationService.updateIsResponsible(companyUserRelationList);

    }

    public void saveCompanyUserIdentity(Long onlineUserId,Long companyId){
        CompanyUserRelation companyUserRelation = CompanyUserRelation.builder()
                .onlineUserId(onlineUserId)
                .isResponsible(0)
                .gmtCreate(new Date())
                .companyId(companyId)
                .build();
        companyUserRelationService.insertCompanyRelation(companyUserRelation);

        UserIdentity userIdentity = UserIdentity.builder()
                .identityId(2)
                .onlineUserId(onlineUserId)
                .build();
        userIdentityService.insertIdentity(userIdentity);
    }
}
