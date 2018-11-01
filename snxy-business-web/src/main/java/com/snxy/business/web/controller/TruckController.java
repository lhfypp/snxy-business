package com.snxy.business.web.controller;

import com.snxy.business.service.TruckTypeService;
import com.snxy.business.service.vo.TruckVo;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/truck")
public class TruckController {
    @Resource
    private TruckTypeService truckTypeService;

    @RequestMapping(value = "/list")
    public ResultData selectTruckType(){
        List<TruckVo> truckTypes = truckTypeService.selectTruckType();
        return ResultData.success(truckTypes);
    }
}
