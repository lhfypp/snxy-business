package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableTempMapper;
import com.snxy.business.domain.VegetableTemp;
import com.snxy.business.service.VegetableTempService;
import com.snxy.business.service.vo.NewVegetableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class VegetableTempServiceImpl implements VegetableTempService {
    @Resource
    private VegetableTempMapper vegetableTempMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newGoods(NewVegetableVO newVegetableVO) {
        VegetableTemp vegetableTemp = VegetableTemp.builder()
                .vegetableName(newVegetableVO.getVegetableName())
                .createSourceId(newVegetableVO.getDeliveryOrderId())
                .gmtCreate(new Date())
                .sourceType(1)
                .status(0)
                .build();
        vegetableTempMapper.insertSelective(vegetableTemp);
    }
}
