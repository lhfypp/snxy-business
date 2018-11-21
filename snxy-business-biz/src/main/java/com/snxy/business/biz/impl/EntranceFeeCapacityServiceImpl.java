package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.EntranceFeeCapacityMapper;
import com.snxy.business.domain.EntranceFeeCapacity;
import com.snxy.business.service.EntranceFeeCapacityService;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class EntranceFeeCapacityServiceImpl implements EntranceFeeCapacityService {

    @Resource
    private EntranceFeeCapacityMapper entranceFeeCapacityMapper;

    @Override
    public List<EntranceFeeCapacity> carTypeList() {
        List<EntranceFeeCapacity> entranceFeeCapacityList = entranceFeeCapacityMapper.selectcarList();
        if (entranceFeeCapacityList == null){
            throw new BizException("获取失败");
        }
        return entranceFeeCapacityList;
    }

    @Override
    public List<EntranceFeeCapacity> selectAll() {
        List<EntranceFeeCapacity> entranceFeeCapacities = entranceFeeCapacityMapper.selectAll();
        return entranceFeeCapacities;
    }
}
