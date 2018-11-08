package com.snxy.business.dao.mapper;

<<<<<<< HEAD
import com.snxy.business.domain.CompanyPartInfo;
import com.snxy.business.domain.MerchantCompany;
import org.apache.ibatis.annotations.Param;
=======
import com.snxy.business.domain.MerchantCompany;
>>>>>>> 0715307c3886f405043faeee6e33e8c66c8ff20e

import java.util.List;

public interface MerchantCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantCompany record);

    int insertSelective(MerchantCompany record);

    MerchantCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantCompany record);

    int updateByPrimaryKey(MerchantCompany record);
<<<<<<< HEAD
    List<CompanyPartInfo> selectAllComInfoByComName(@Param("companyName") String companyName);
=======

    List<String> selectAllName();
>>>>>>> 0715307c3886f405043faeee6e33e8c66c8ff20e
}