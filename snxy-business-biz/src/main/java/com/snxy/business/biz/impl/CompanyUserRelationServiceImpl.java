package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CompanyUserRelationMapper;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.CompanyUserRelationService;

import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.UserImageService;
import com.snxy.business.service.vo.PersonalVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;




import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Slf4j
public class CompanyUserRelationServiceImpl implements CompanyUserRelationService {
    @Resource
    private CompanyUserRelationMapper companyUserRelationMapper;
    @Resource
    private UserImageService userImageService;
    @Resource
    private OnlineUserService onlineUserService;

    @Override
    public int insert(CompanyUserRelation record) {
        return companyUserRelationMapper.insert(record);

    }

    /**
     * 根据在线id查询商户我的公司信息
     * @param onlineUserId
     * @return
     */
    @Override
    public MerchantCompany selectBossCompanyByUserId(Long onlineUserId ) {
        return companyUserRelationMapper.selectBossCompanyByUserId(onlineUserId);
    }

    /**
     * 商户删除员工
     * @param onlineUserId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCompanyUserRelationByOnlineUserId(Long onlineUserId) {
       companyUserRelationMapper.deleteCompanyUserRelationByOnlineUserId(onlineUserId);
    }

    /**
     * 根据onlineUserId查询员工所属公司的信息
     * @param onlineUserId
     * @return
     */
    @Override
    public MerchantCompany selectEmployCompanyByUserId(Long onlineUserId) {
        return companyUserRelationMapper.selectEmployCompanyByUserId(onlineUserId);
    }

    /**
     * 根据onlineUserId查询companyId
     * @param onlineUserId
     * @return
     */
    @Override
    public List<Long> selectCompanyIdByOnlineUserId(Long onlineUserId) {

        return companyUserRelationMapper.selectCompanyIdByOnlineUserId(onlineUserId);
    }

    /**
     * 查询商户个人信息
     * @param systemUserId
     * @return
     */
    @Override
    public PersonalVO selectPersonalByOnlineUserId(Long systemUserId) {
//        根据systemUserId去userImage表中查出用户头像
        String userImage = userImageService.selectImageBySystemUserId(systemUserId);
//        根据systemUserId查询onlineUserId
        Long onlineUserId = onlineUserService.selectOnlineUserIdBySystemUserId(systemUserId);
//        根据company_user_relation和online_user两张表中onlineUserId相等，得到userName
        String bossName = companyUserRelationMapper.selectUserNameByOlineUserId(onlineUserId);
//        查询电话号
        String bossPhone = companyUserRelationMapper.selectPhoneByOnlineUserId(onlineUserId);

        PersonalVO personalVO = new PersonalVO();
        personalVO.setUserName(bossName);
        personalVO.setPhone(bossPhone);
        personalVO.setUrl(userImage);
//        因为用户身份标签放到前台的请求中，不用后台查询
        return personalVO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCompanyUserRelation(CompanyUserRelation companyUserRelation) {
        companyUserRelationMapper.insert(companyUserRelation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCompanyRelation(CompanyUserRelation companyUserRelation) {
        companyUserRelationMapper.insertCompanyRelation(companyUserRelation);
    }

    @Override
    public CompanyUserRelation selectCompanyUserRelation(Long id) {
        CompanyUserRelation companyUserRelation = companyUserRelationMapper.selectByOnlineUserId(id);
        return companyUserRelation;
    }

    @Override
    public void insertCompanyRelationList(List<CompanyUserRelation> companyUserRelationList) {
        companyUserRelationMapper.insertCompanyRelationList(companyUserRelationList);
    }

    @Override
    public List<CompanyUserRelation> selectAllByCompanyId(Long companyId) {
        List<CompanyUserRelation> companyUserRelationList = companyUserRelationMapper.selectAllByCompanyId(companyId);
        return companyUserRelationList;
    }

    @Override
    public void updateIsResponsible(List<CompanyUserRelation> companyUserRelationList) {
        companyUserRelationMapper.updateByCompanyUserRelationList(companyUserRelationList);

    }
}
