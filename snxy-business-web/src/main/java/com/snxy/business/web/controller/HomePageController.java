package com.snxy.business.web.controller;

import com.snxy.business.service.*;
import com.snxy.business.service.vo.*;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/homePage")
public class HomePageController {
    @Resource
    private DeliveryOrderService deliveryOrderService;
    @Resource
    private VegetableDeliveryRelationService vegetableDeliveryRelationService;
    @Resource
    private VegetableCategoryService vegetableCategoryService;
    @Resource
    private VegetableService vegetableService;
    @Resource
    private VegetableTempService vegetableTempService;
    @Resource
    private VehicleService vehicleService;

    /*
    * 获取当前最需要最新订单（一张）
    * */
    @RequestMapping("/order/show/newest")
    public ResultData showNewestOrder(@RequestAttribute("systemUser")SystemUserVO systemUserVO){
        HomePageOrderVO homePageOrderVO = deliveryOrderService.showNewestOrder(systemUserVO);
        return ResultData.success(homePageOrderVO);
    }

    /*
    * 新建订单时系统默认生成数据
    * */
//    @RequestMapping("/order/bill/new")
//    public ResultData createOrderNo (@RequestAttribute("systemUser")SystemUserVO systemUserVO){
//        BillVO billVO = deliveryOrderService.createOrderNo(systemUserVO.getOnlineUserId());
//        return ResultData.success(billVO);
//    }

    /*
    * 发布保存订单
    * */
    @RequestMapping(value = "/order/save")
    public ResultData saveDeliveryOrder (@RequestAttribute("systemUser")SystemUserVO systemUserVO, DeliveryOrderVo deliveryOrderVo){
        deliveryOrderService.saveDeliveryOrder(systemUserVO,deliveryOrderVo);
        return ResultData.success("");
    }

    /*
    * 常用货品展示
    * */
    @RequestMapping("/order/commonly/goods")
    public ResultData showCommonlyGoods(@RequestAttribute("systemUser")SystemUserVO systemUserVO){
        List<Goods> goods = vegetableDeliveryRelationService.selectByDeliveryId(systemUserVO);
        return ResultData.success(goods);
    }

    /*
    * 添加货品界面类别展示
    * */
    @RequestMapping("/order/goods/category/show")
    public ResultData showAllCategory(){
        List<CategoryVO> categoryVOList = vegetableCategoryService.selectAllCategory();
        return ResultData.success(categoryVOList);
    }

    /*
    * 具体货品展示
    * */
    @RequestMapping("/order/goods/show")
    public ResultData showAllGoods(Long vegetableCategoryId,Integer pageNum,Integer pageSize){
        PageInfo<Goods> goodsPageInfo = vegetableService.selectAllGoodsByCategoryId(vegetableCategoryId,pageNum,pageSize);
        return ResultData.success(goodsPageInfo);
    }

    /*
    * 货品信息搜索
    * */
    @RequestMapping(value = "/order/selectGoodsName")
    public ResultData selectCompanyGoodsByGoodsNameAndCompanyId (String vegetableName){
        List<Goods> goodsList = vegetableService.selectByVegetableName(vegetableName);
        return ResultData.success(goodsList);
    }

    /*
    * 新建货品
    * */
    @RequestMapping("/order/goods/new")
    public ResultData newGoods(NewVegetableVO newVegetableVO){
        vegetableTempService.newGoods(newVegetableVO);
        return ResultData.success("");
    }

    /*
    * 司机确认发货前车辆选择
    * */
    @RequestMapping("/order/vehicle/show")
    public ResultData showVehicle(@RequestAttribute("systemUser")SystemUserVO systemUserVO){
        List<CarVO> carVOList = vehicleService.selectByDriverId(systemUserVO.getOnlineUserId());
        return ResultData.success(carVOList);
    }

    /*
    * 司机选择车辆确认发货
    * 商户确认订单，商确认订单时不需传递车辆信息
    * */
    @RequestMapping("/order/driver/confirmation")
    public ResultData confirmationOrder(@RequestAttribute("systemUser")SystemUserVO systemUserVO,@RequestBody DriverConfirmationVO driverConfirmationVO){
        deliveryOrderService.confirmationOrder(systemUserVO,driverConfirmationVO);
        return ResultData.success("");
    }

    /*
    * 发货订单详细信息
    * */
    @RequestMapping("/order/details")
    public ResultData showOrderDetails(Long deliveryOrderId){
        DeliveryOrderMessageVO deliveryOrderMessageVO = deliveryOrderService.showOrderDetails(deliveryOrderId);

        return ResultData.success(deliveryOrderMessageVO);
    }

    /*
    * 查看订单列表
    * */
    @RequestMapping("/order/list/show")
    public ResultData showOrderList(@RequestAttribute("systemUser")SystemUserVO systemUserVO,Integer pageNum,Integer pageSize,Integer status){
        PageInfo<HomePageOrderVO> homePageOrderVO = deliveryOrderService.showOrderList(systemUserVO,pageNum,pageSize,status);
        return ResultData.success(homePageOrderVO);
    }
}
