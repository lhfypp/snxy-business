package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.GuaranteeDepositMapper;
import com.snxy.business.domain.GuaranteeDeposit;
import com.snxy.business.service.GuaranteeDepositService;
import com.snxy.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.prefs.BackingStoreException;

@Service
@Slf4j
public class GuaranteeDepositServiceImpl implements GuaranteeDepositService {
    @Resource
    private GuaranteeDepositMapper guaranteeDepositMapper;

    @Override
    public List<Long> searchOrderIds() {
         return guaranteeDepositMapper.selectOrderId();
    }

    @Override
    public int updateIsGenerateQuality(Long orderId) {

        if(1!=guaranteeDepositMapper.updateIsGenerateQuality(orderId)){
            throw new BizException("更新是否生成待检测单失败");
        }
        return 1;
    }

    @Override
    public GuaranteeDeposit selectGuaranteeByOrderNo(String orderNo) {
        if (orderNo==null){
            throw new BizException("货运单号不能为空");
        }
       GuaranteeDeposit guaranteeDeposit =  guaranteeDepositMapper.selectGuaranteeByOrderNo(orderNo);
        return guaranteeDeposit;
    }
}
