package com.snxy.business.dao.mapper;

import com.snxy.business.domain.CompanyUserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface    CompanyUserRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyUserRelation record);

    int insertSelective(CompanyUserRelation record);

    CompanyUserRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyUserRelation record);

    int updateByPrimaryKey(CompanyUserRelation record);

    long selectCompanyId(Long onlineUserId);
    List<Long> selectOnlinUserId(Long companyId);
	Long selectCompanyByOnlineId(Long id);

    void changePrinciple(@Param("companyId") Integer companyId, @Param("onlineUserId") Long onlineUserId, @Param("isResponsible") Integer isResponsible);

    void updateByOnlineUserId(CompanyUserRelation companyUserRelation);

    void deleteByOnlineUserId(@Param("onlineUserId") Long onlineUserId, @Param("isDelete") byte isDelete);
}