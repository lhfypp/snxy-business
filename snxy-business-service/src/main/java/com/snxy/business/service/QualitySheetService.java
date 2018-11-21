package com.snxy.business.service;

import com.snxy.business.domain.QualitySheet;
import com.snxy.common.util.PageInfo;


import com.snxy.business.service.vo.CheckSheetVO;
import com.snxy.business.service.vo.SystemUserVO;


public interface QualitySheetService {
    QualitySheet qualitySheetByOrderId(Long checkId);

    PageInfo<QualitySheet> qualitySheetList(Long onlineUserId);

    String createQualitySheet(CheckSheetVO checkSheetVO,Long userId);

    PageInfo searchWillBeQualitySheet(SystemUserVO systemUserVO,String searchName);

    PageInfo searchQualitySheet(SystemUserVO systemUserVO,String searchName);

    int deleteQualitySheetById(String qualitySheetId);

    String createQualitySheet(String deliveryOrderId);

    String generateCheckOderNo();

    PageInfo searchQualitySheetReportList(SystemUserVO systemUserVO);

}
