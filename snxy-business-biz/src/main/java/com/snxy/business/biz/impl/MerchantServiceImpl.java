package com.snxy.business.biz.impl;

import com.snxy.business.biz.config.IdentityTypeEnum;
import com.snxy.business.biz.feign.FilePicService;
import com.snxy.business.biz.feign.FileService;

import com.snxy.business.biz.feign.MessagePushService;
import com.snxy.business.biz.feign.SmsService;
import com.snxy.business.domain.*;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.*;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.MD5Util;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private FilePicService filePicService;
    @Resource
    private SmsService smsService;
    @Resource
    private MessagePushService messagePushService;
    @Resource
    private MessageService messageService;
    @Resource
    private UserUmengRelationService userUmengRelationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long newCompany(MultipartFile file, NewCompanyVO newCompanyVO, SystemUserVO systemUserVO) {
        log.info("newCompanyVO [{}] :",newCompanyVO);

        //加上判断是否填写信息===姓名不能为空
        if(StringUtil.isBlank(newCompanyVO.getMerchantName())){
            throw new BizException("公司名称为空");
        }
//        //判断公司名为全中文
//        if(!StringUtil.isChinese(newCompanyVO.getMerchantName())){
//            throw new BizException("请输入中文公司名");
//        }
        //判断公司名长度
        if(newCompanyVO.getMerchantName().length()>20){
            throw new BizException("公司名长度应为20位以内");
        }
//        if(newCompanyVO.getHeadName().length()>5||!StringUtil.isChinese(newCompanyVO.getHeadName())){
//            throw new BizException("请输入5位以内中文姓名");
//        }

        //上传图片
        ResultData<String> upload = fileService.upload(file);
        if (!upload.isResult()) {
            throw new BizException(upload.getMsg());
        }
        String url = upload.getData();

        //需要判断社会信息码是否正确，不正确的话其他信息正常存入，信息码不存

        MerchantCompany merchantCompany = MerchantCompany.builder()
                .certificationStatus((byte)2)
                .gmtCreate(new Date())
                .corporateCertificationUrl(url)
                .merchantName(newCompanyVO.getMerchantName())
                .build();
        if(newCompanyVO.getSocialInfoCode().length()==18){
            merchantCompany.setSocialInfoCode(newCompanyVO.getSocialInfoCode());
            merchantCompany.setCertificationStatus((byte)1);
        }

        merchantCompanyService.insertCompanyMessage(merchantCompany);

        //判断负责人是否为当前用户
        CompanyUserRelation companyUserRelation = CompanyUserRelation.builder()
                .companyId(merchantCompany.getId())
                .gmtCreate(new Date())
                .build();

        if(newCompanyVO.getHeadPhone().equals(systemUserVO.getPhone())) {
            //如果是，则设置为负责人
            userIdentityService.updateIdentityByOnlineUserId(systemUserVO.getOnlineUserId(), IdentityTypeEnum.Head.getIdentityTypeId());
        }
        //判断传入的姓名手机号有没有注册
        OnlineUser onlineUser = onlineUserService.selectByPhone(newCompanyVO.getHeadPhone());
        if(onlineUser==null){
            SystemUser systemUser = SystemUser.builder()
                    .account(newCompanyVO.getHeadPhone())
                    .chineseName(newCompanyVO.getHeadName())
                    .mobile(newCompanyVO.getHeadPhone())
                    .regDate(new Date())
                    .pwd(MD5Util.encrypt("111111"))
                    .accountStatus((byte)0)
                    .gmtCreate(new Date())
                    .build();
            systemUserService.insertSystemUser(systemUser);

            OnlineUser onlineUser1 = OnlineUser.builder()
                    .userName(newCompanyVO.getHeadName())
                    .phone(newCompanyVO.getHeadPhone())
                    .systemUserId(systemUser.getId())
                    .build();
            onlineUserService.insertOnlineUser(onlineUser1);
            //没有注册，则为其手机号注册，并给该手机号发送短信，typeId换成APP下载短信的模板
            String message = "您已被添加为公司负责人，请下载新发地APP";
            smsService.sendSmsCode(newCompanyVO.getHeadPhone(), message, 1L);

            companyUserRelation.setOnlineUserId(onlineUser1.getId());
        }else {
            //已注册调用消息推送，判断是否已经绑定公司
            CompanyUserRelation companyUserRelation1 = companyUserRelationService.selectByOnlineUserId(onlineUser.getId());
            if(companyUserRelation1!=null){
                throw new BizException("负责人用户已绑定其他公司，请进行核实");
            }
            companyUserRelation.setOnlineUserId(onlineUser.getId());
            //消息推送

            String ticker = "您已被设置成为公司负责人";
            String title = "成为公司负责人";
            String remark = "备注";
            List<String> phoneList = new ArrayList<>();
            phoneList.add(newCompanyVO.getHeadPhone());
            pushMessage(phoneList,ticker,title,remark);

        }
        companyUserRelation.setIsResponsible(1);
        companyUserRelation.setIsFounder(1);
        companyUserRelationService.insertCompanyUserRelation(companyUserRelation);
        return merchantCompany.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newOneEmployee(EmployeeVO employeeVO) {
        OnlineUser onlineUser = onlineUserService.selectByPhone(employeeVO.getPhone());
        if(onlineUser==null){
            SystemUser systemUser = SystemUser.builder()
                    .account("系统生成账号"+employeeVO.getPhone())
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

    @Override
    public List<MailVO> showMail(List<MailVO> mailVOList,Long companyId) {
        //通讯录用户手机号集合
        List<String> phoneList = mailVOList.parallelStream().map(mailVO -> mailVO.getPhone()).collect(Collectors.toList());
        //通讯录已注册用户集合
        List<OnlineUser> onlineUserList = onlineUserService.selectByPhoneList(phoneList);
        //通讯录已注册用户id集合
        List<Long> onlineUserIdList = onlineUserList.parallelStream().map(onlineUser -> onlineUser.getId()).collect(Collectors.toList());
        //通讯录已绑定公司用户集合
        List<CompanyUserRelation> companyUserRelationList = companyUserRelationService.selectUserRelationByOnlineUserIdList(onlineUserIdList);

        Map<Long,String> onlineUserIdNameMap = onlineUserList.parallelStream().collect(Collectors.toMap(OnlineUser::getId,OnlineUser::getUserName));
        Map<Long,String> onlineUserIdPhoneMap = onlineUserList.parallelStream().collect(Collectors.toMap(OnlineUser::getId,OnlineUser::getPhone));
        List<MailVO> mailVOS = companyUserRelationList.parallelStream().map(companyUserRelation -> MailVO.builder()
                         .name(onlineUserIdNameMap.get(companyUserRelation.getOnlineUserId()))
                         .phone(onlineUserIdPhoneMap.get(companyUserRelation.getOnlineUserId()))
                         .responsibleType(companyUserRelation.getCompanyId().equals(companyId) ? (byte)1 : (byte)2)
                         .build())
                         .collect(Collectors.toList());

        //通讯录已绑定公司用户id集合
        List<Long> companyUserRelationIdList = companyUserRelationList.parallelStream().map(companyUserRelation -> companyUserRelation.getOnlineUserId()).collect(Collectors.toList());
        //通讯录已绑定公司用户phone集合
        List<OnlineUser> onlineUserList1 = onlineUserService.selectByOnlineUserIdList(companyUserRelationIdList);
        List<String> onlineUserPhoneList = onlineUserList1.parallelStream().map(onlineUser -> onlineUser.getPhone()).collect(Collectors.toList());
        //通讯录未绑定用户手机号集合
        phoneList.removeAll(onlineUserPhoneList);
        //所有名字手机号map
        Map<String,String> phoneNameMap = mailVOList.parallelStream().collect(Collectors.toMap(MailVO::getPhone,MailVO::getPhone));
        //未绑定用户的
        List<MailVO> mailVOS1 = phoneList.parallelStream().map(s -> MailVO.builder()
                         .name(phoneNameMap.get(s))
                         .phone(s)
                         .responsibleType((byte)0)
                         .build())
                         .collect(Collectors.toList());
        mailVOS.addAll(mailVOS1);
        return mailVOS;
    }

    @Override
    public List<CompanyVO> companyExist(String companyName,Integer showNum) {
        //判断公司名是否存在
        List<CompanyVO> companyVOList = merchantCompanyService.selectByCompanyName(companyName);
        log.info("companyVOList : [{}]",companyVOList);
        if(companyVOList.size()>showNum){
            return companyVOList.subList(0,showNum);
        }

        return companyVOList;
    }

    @Override
    public CompanyVO showCompany(Long companyId) {
        MerchantCompany merchantCompany = merchantCompanyService.selectByCompanyId(companyId);

        //查询公司负责人
        CompanyUserRelation companyUserRelation = companyUserRelationService.selectUserRelationByCompanyId(companyId);
        OnlineUser onlineUser = new OnlineUser();
        if (companyUserRelation!=null) {
            onlineUser = onlineUserService.selectByOnlineUserId(companyUserRelation.getOnlineUserId());
        }

        CompanyVO companyVO = CompanyVO.builder()
                .companyId(companyId)
                .companyName(merchantCompany.getMerchantName())
                .certificationStatus(merchantCompany.getSocialInfoCode()!=null ? true : false)
                .headName(onlineUser.getUserName())
                .headPhone(onlineUser.getPhone())
                .build();
        return companyVO;
    }

    @Override
    public void employeeAdd(Long companyId, Long onlineUserId) {
        CompanyUserRelation companyUserRelation = CompanyUserRelation.builder()
                .onlineUserId(onlineUserId)
                .companyId(companyId)
                .isResponsible(0)
                .gmtCreate(new Date())
                .isFounder(0)
                .build();
        List<CompanyUserRelation> companyUserRelationList = companyUserRelationService.selectFounderByCompanyId(companyId);
        List<Long> onlineUserIdList = companyUserRelationList.parallelStream().map(CompanyUserRelation::getOnlineUserId).collect(Collectors.toList());
        List<OnlineUser> onlineUserList = onlineUserService.selectByOnlineUserIdList(onlineUserIdList);
        List<String> phoneList = onlineUserList.parallelStream().map(OnlineUser::getPhone).collect(Collectors.toList());
        String ticker = "XXX申请加入公司";
        String title = "申请加入公司";
        String remark = "备注";
        pushMessage(phoneList,ticker,title,remark);
        companyUserRelationService.insertCompanyUserRelation(companyUserRelation);
    }

    @Override
    public SocialVO distinguishSocial(MultipartFile file) {
        ResultData<BusinessLicenseVO> resultData = filePicService.businessFront(file);
        SocialVO socialVO = new SocialVO();
        socialVO.setIsTrue((byte)1);
        if (!resultData.isResult()) {
            socialVO.setIsTrue((byte)0);
        }
        BusinessLicenseVO data = resultData.getData();
        if (data!=null) {
            socialVO.setSocialCreditCode(data.getSocialCreditCode());
        }

        return socialVO;
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

    //批量推送
    public void pushMessage(List<String> phoneList,String ticker,String title,String remark){
        List<SystemUser> systemUserList = systemUserService.selectByMobileList(phoneList);
        List<Long> systemUserIdList = systemUserList.parallelStream().map(SystemUser::getId).collect(Collectors.toList());
        List<UserUmengRelation> userUmengRelationList = userUmengRelationService.selectBySystemUserIdList(systemUserIdList);
        List<MessageInfo.DeviceInfo> deviceInfoList = userUmengRelationList.parallelStream().map(s -> MessageInfo.DeviceInfo.builder()
                                   .deviceToken(s.getDeviceToken())
                                   .phoneType(s.getPhoneType())
                                   .build())
                                   .collect(Collectors.toList());

        MessageInfo messageInfo = MessageInfo.builder()
                .ticker(ticker)
                .title(title)
                .remark(remark)
                .deviceInfos(deviceInfoList)
                .build();
        messagePushService.pushMessage(messageInfo);
        List<OnlineUser> onlineUserList = onlineUserService.selectByPhoneList(phoneList);
        List<Message> messageList = onlineUserList.parallelStream().map(s -> Message.builder()
                                       .sender(s.getId())
                                       .content(ticker)
                                       .gmtCreate(new Date())
                                       .remark(remark)
                                       .title(title)
                                       .build())
                                       .collect(Collectors.toList());
        messageService.insertMessageList(messageList);
    }

}
