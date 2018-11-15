package com.snxy.business.service;

import com.snxy.business.service.vo.CheckSheetVO;
import com.snxy.business.service.vo.SystemUserVO;
import com.snxy.common.util.PageInfo;

public interface QualitySheetService {
    String createQualitySheet(CheckSheetVO checkSheetVO,Long userId);
    PageInfo searchWillBeQualitySheet(SystemUserVO systemUserVO,String searchName);
    PageInfo searchQualitySheet(SystemUserVO systemUserVO,String searchName);
    int    deleteQualitySheetById(String qualitySheetId);
}
