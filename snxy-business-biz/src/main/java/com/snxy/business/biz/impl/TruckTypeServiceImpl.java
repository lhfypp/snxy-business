package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.TruckTypeMapper;
import com.snxy.business.domain.TruckType;
import com.snxy.business.service.TruckTypeService;
import com.snxy.business.service.vo.TruckVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TruckTypeServiceImpl implements TruckTypeService {

    @Resource
    private TruckTypeMapper truckTypeMapper;

    @Override
    public List<TruckVo> selectTruckType() {
        List<TruckType> truckTypes = truckTypeMapper.selectAll();
        List<TruckVo> truckVoList = new ArrayList<>();
        for (int i = 0; i < truckTypes.size(); i++) {
            TruckVo truckVo = new TruckVo();
            BeanUtils.copyProperties(truckTypes.get(i),truckVo);
            truckVoList.add(truckVo);
        }
        return truckVoList;
    }

    @Override
    public TruckType selectTruckTypeByTruckId(long id) {
        return truckTypeMapper.selectByPrimaryKey(id);
    }
}
