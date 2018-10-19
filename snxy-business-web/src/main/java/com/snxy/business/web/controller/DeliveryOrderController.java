package com.snxy.business.web.controller;

import com.snxy.business.domain.*;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.CompanyVegetableService;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/*
* 发货订单管理
* */
@RestController
@Slf4j
@RequestMapping("/delivery")
public class DeliveryOrderController {

    @Resource
    private DeliveryOrderService deliveryOrderService;

    @Resource
    private CompanyVegetableService companyVegetableService;

    @Resource
    private CompanyUserRelationService companyUserRelationService;

    @RequestMapping("/test")
    public ResultData test(){
        return ResultData.success("ahha");
    }


    //货品信息添加页展示
    @RequestMapping(value = "/seller/showGoods")
    public ResultData showGoods (Long userCompanyId){
        List<CompanyVegetable> companyVegetableList =  companyVegetableService.showGoods(1L);
        return ResultData.success(companyVegetableList);
    }

    //货品信息搜索
    @RequestMapping(value = "/seller/selectByGoodsName")
    public ResultData selectByGoodsName (String goodsName){
        CompanyVegetable companyVegetable = companyVegetableService.selectByGoodsName("大白菜");
        return ResultData.success(companyVegetable);
    }

    //新建发货订单
    @RequestMapping(value = "/seller/bill/new")
    public ResultData createDeliveryOrder (Integer deviceType,DeliveryOrderVo deliveryOrderVo){
        //先进行权限判断，是否为商户或者代办，公司信息是否完整，设置商户，代办身份标识为0，1
        deviceType = 1;
        if(deviceType!=0 && deviceType!=1){
            return ResultData.fail("你还没有完成认证");
        }
        //查询是否完成公司信息填写
        List<Long> list = companyUserRelationService.selectCompanyIsExist(1L);
        if(list==null || list.size()==0){
            return ResultData.fail("你还没有完成认证");
        }

        //如果已认证则新建一个订单,此处系统生成订单号暂时写死

        String orderNo = deliveryOrderService.getOrderNo();

        deliveryOrderVo.setOrderNo(orderNo);
        return ResultData.success(deliveryOrderVo);
    }

    //保存发布订单
    @RequestMapping(value = "/seller/bill/save")
    public ResultData saveDeliveryOrder (DeliveryOrderVo deliveryOrderVo){

        deliveryOrderService.createDeliveryOrder(deliveryOrderVo);

        return ResultData.success("订单发布成功");
    }

    @RequestMapping(value = "/seller/order/list")
    public ResultData<List<BillInfo>> searchDeliveryOrder(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter("userId"));//用户标识
        String orderStatus = request.getParameter("orderStatus");//订单状态
        String serchName = request.getParameter("serchName");//地点or联系人or单号


        ResultData<List<BillInfo>> listResultData = new ResultData<List<BillInfo>>();
        listResultData.setData(deliveryOrderService.searchDeliveryOrder(userId, orderStatus, serchName));

        return listResultData;
    }

    @RequestMapping("/failed")
    public ResultData requestFailed() {


        return ResultData.fail("用户id为空");
    }
}