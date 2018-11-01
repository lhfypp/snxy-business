package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.dao.mapper.MerchantCompanyMapper;
import com.snxy.business.domain.CompanyUserRelation;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.MerchantCompanyService;
import com.snxy.business.service.vo.MerchantCompanyVo;
import com.snxy.business.service.vo.ModifyCompanyMessageVo;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MerchantCompanyServiceImpl implements MerchantCompanyService {

    @Resource
    private MerchantCompanyMapper merchantCompanyMapper;
    @Resource
    private CompanyUserRelationService companyUserRelationService;
    @Resource
    private FileService fileService;

    @Override
    public MerchantCompanyVo selectByPrimaryKey(Long id) {
        Long companyId = companyUserRelationService.selectByOnlineId(id);

        MerchantCompany merchantCompany = merchantCompanyMapper.selectByPrimaryKey(companyId);

            MerchantCompanyVo merchantCompanyVo = new MerchantCompanyVo();
            BeanUtils.copyProperties(merchantCompany,merchantCompanyVo);

        return merchantCompanyVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNewCompany(MerchantCompanyVo merchantCompanyVo) {
        ResultData<String> upload = fileService.upload(merchantCompanyVo.getFile());
        if (!upload.isResult()) {
            throw new BizException(upload.getMsg());
        }
        String url = upload.getData();
        MerchantCompany merchantCompany = new MerchantCompany();
        BeanUtils.copyProperties(merchantCompanyVo,merchantCompany);
        merchantCompany.setCorporateCertificationUrl(url);
        merchantCompany.setGmtCreate(new Date());
        merchantCompany.setCertificationStatus((byte)0);
        merchantCompany.setIsDelete((byte)0);

        merchantCompanyMapper.insert(merchantCompany);
        Long merchantCompanyId = merchantCompany.getId();
        CompanyUserRelation companyUserRelation = CompanyUserRelation.builder()
                .companyId(merchantCompanyId)
                .companyType(merchantCompanyVo.getMerchantType())
                .onllineUserId(merchantCompanyVo.getOnlineUserId())
                .gmtCreate(new Date())
                .isDelete((byte)0)
                .isResponsible(0)
                .build();
        companyUserRelationService.insertCompany(companyUserRelation);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyCompanyMessage(ModifyCompanyMessageVo modifyCompanyMessageVo) {
        MerchantCompany merchantCompany = new MerchantCompany();
        BeanUtils.copyProperties(modifyCompanyMessageVo,merchantCompany);
        merchantCompany.setGmtModified(new Date());
        merchantCompanyMapper.updateByPrimaryKey(merchantCompany);
    }
}
