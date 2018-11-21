package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.GateMapper;
import com.snxy.business.domain.Gate;
import com.snxy.business.service.GateService;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class GateServiceImpl implements GateService {
    @Resource
    private GateMapper doorMapper;

    /**
     * 获取新发地所有门的gps位置
     * @return
     */
    @Override
    public List<Gate> selectAllDoorGps() {
       List<Gate> gateGpsList = doorMapper.selectAllGateGps();
       if (gateGpsList.size()==0 ||gateGpsList.isEmpty()){
           throw new BizException("不好意思，您还没有录入gps位置");
       }
        return gateGpsList;
    }
}
