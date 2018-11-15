package com.snxy.business.biz.impl;

import com.snxy.business.domain.QualitySheet;
import com.snxy.business.service.QualitySheetService;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest()
@RunWith(SpringRunner.class)
@Slf4j
public class QualitySheetServiceTest {

    @Resource
    private QualitySheetService qualitySheetService;

    @Test
    public void QualitySheetTest(){
        QualitySheet qualitySheet = qualitySheetService.qualitySheetByOrderId(12L);
        log.info(qualitySheet.getCategoryName());
        log.info(qualitySheet.getCode());
        log.info(qualitySheet.getProductionLocation());
        log.info(qualitySheet.getQrcodeUrl());
        log.info(qualitySheet.getId().toString());
        log.info(qualitySheet.getDeliveryOrderId().toString());
        log.info(qualitySheet.getIsDelete().toString());
        log.info(qualitySheet.getWeight().toString());
        log.info(qualitySheet.getStatus().toString());
    }

    @Test
    public void qualitySheetList(){
        PageInfo<QualitySheet> qualitySheetList = qualitySheetService.qualitySheetList(1L);
        log.info("查询条数："+qualitySheetList.getTotal());
    }
}
