package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.MerchantCompanyMapper;
import com.snxy.business.domain.MerchantCompany;
import com.snxy.business.service.MerchantCompanyService;
import com.snxy.business.service.vo.CompanyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MerchantCompanyServiceImpl implements MerchantCompanyService {
    @Resource
    private MerchantCompanyMapper merchantCompanyMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCompanyMessage(MerchantCompany merchantCompany) {
        merchantCompanyMapper.insertSelective(merchantCompany);
    }

    @Override
    public List<String> selectAllName() {
        List<String> nameList = merchantCompanyMapper.selectAllName();
        return nameList;
    }

    @Override

    public String searchNameById(Long id) {
        return merchantCompanyMapper.selectByPrimaryKey(id).getMerchantName();

    }
    public MerchantCompany selectByCompanyId(Long companyId) {
        MerchantCompany merchantCompany = merchantCompanyMapper.selectByPrimaryKey(companyId);
        return merchantCompany;
    }

    @Override
    public List<CompanyVO> selectByCompanyName(String companyName) {
        List<MerchantCompany> merchantCompanyList = merchantCompanyMapper.selectByCompanyName(companyName);
        List<CompanyVO> companyVOList = merchantCompanyList.parallelStream().map(merchantCompany -> CompanyVO.builder()
                .companyId(merchantCompany.getId())
                .companyName(merchantCompany.getMerchantName())
                .build())
                .collect(Collectors.toList());
        return companyVOList;
    }
    public List<MerchantCompany> selectCompanyByCompanyIdList(List<Long> companyIdList) {

        return  merchantCompanyMapper.selectCompanyByCompanyIdList(companyIdList);

    }
}
