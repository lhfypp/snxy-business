package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.DoorMapper;
import com.snxy.business.domain.Door;
import com.snxy.business.service.DoorService;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DoorServiceImpl implements DoorService {
    @Resource
    private DoorMapper doorMapper;

    /**
     * 获取新发地所有门的gps位置
     * @return
     */
    @Override
    public List<Door> selectAllDoorGps() {
       List<Door> doorGpsList = doorMapper.selectAllDoorGps();
       if (doorGpsList.size()==0 ||doorGpsList.isEmpty()){
           throw new BizException("不好意思，您还没有录入gps位置");
       }
        return doorGpsList;
    }
}
