package com.snxy.business.dao.mapper;

import com.snxy.business.domain.DeliveryOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeliveryOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeliveryOrder record);

    int insertSelective(DeliveryOrder record);

    DeliveryOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeliveryOrder record);

    int updateByPrimaryKey(DeliveryOrder record);

    List<DeliveryOrder> selectAllByIdAndPhone(@Param("onlineUserId") Long onlineUserId, @Param("phone") String phone);

    List<DeliveryOrder> selectByCompanyId(Long companyId);

}