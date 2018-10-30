package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.TruckTypeMapper;
import com.snxy.business.domain.TruckType;
import com.snxy.business.service.TruckTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TruckTypeServiceImpl implements TruckTypeService {

    @Resource
    private TruckTypeMapper truckTypeMapper;

    @Override
    public List<TruckType> selectTruckType() {
        List<TruckType> truckTypes = truckTypeMapper.selectAll();
        return truckTypes;
    }

    @Override
    public TruckType selectTruckTypeByTruckId(long id) {
        return truckTypeMapper.selectByPrimaryKey(id);
    }
}
