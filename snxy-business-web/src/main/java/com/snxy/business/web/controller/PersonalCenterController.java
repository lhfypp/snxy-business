package com.snxy.business.web.controller;




import com.snxy.business.biz.feign.FileService;
import com.snxy.business.domain.CommonProblems;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.*;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.DateUtil;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * 个人中心
 * @author
 *
 */
@RestController
@Slf4j
@RequestMapping("/person")
@Validated
public class PersonalCenterController {
    @Resource
    private PerSettingsHomepageService PerSettingsHomepageService;
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private UserImageService userImageService;
    @Resource
    private FileService fileService;
    @Resource
    private VersionService versionService;
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private CommonProblemsService commonProblemsService;
    @Resource
    private CustomerMessageService customerMessageService;
    @Resource
    private IdentityTypeService identityTypeService;
    @Resource
    private PersonalCenterService personalCenterService;


    //设置所属商户(搜索)
    @RequestMapping("/company/search")
    public ResultData setTheownerMerchant( String companyName){
        List<CompanyVO> CompanyVO=PerSettingsHomepageService.searchCompanyVO(companyName);

        return ResultData.success(CompanyVO);
    }
    //选择加入商户
    @RequestMapping("/company/save")
   public ResultData saveJoinTheMerchants(@RequestAttribute(value = "systemUser",required = false) SystemUserVO systemUserVO, @NotBlank(message="公司id不能为空") String companyId){
       long userId=onlineUserService.searchOnlineUserId(systemUserVO.getSystemUserId().toString()) ;//查询出在线id

     return ResultData.success(PerSettingsHomepageService.saveJoinTheMerchants(userId,Long.parseLong(companyId)));
   }
    //通知商户模板信息下载
    @RequestMapping("/company/Notify/download")
    public ResultData notifyDownLoadMerchantTemplate(){
     String name=null;
      //查询出姓名
        //查询出商户负责人，通过server层传msg
        return ResultData.success("请下载进出门app");
    }
    //预约办卡
    @RequestMapping("Reserve/card/handle")
    public ResultData handleCardReserve(){
        log.info("通知中信预约办卡");
        return null;
    }
    /**
     * 修改用户头像
     *
     * @param systemUserId
     * @param file
     */
    @RequestMapping("/updateUserImage")
    public void updateUserImage(Long systemUserId, MultipartFile file) {
        log.info("============================");
        userImageService.updateImageById(systemUserId, file);
    }

    /**
     * 修改在线用户姓名
     * @param systemUserId
     * @param name
     */
    @RequestMapping("/updateOnlineUserName")
    public void updateUserName(Long systemUserId, String name){
        onlineUserService.updateName(systemUserId,name);
    }

    /**
     * 修改系统用户姓名
     * @param systemUserId
     * @param name
     */
    @RequestMapping("/updateSysUserName")
    public void updateSysUserName(Long systemUserId, String name){
        systemUserService.updateName(systemUserId,name);
    }

    /**
     * 查询个人信息
     * @param systemUserVO
     * @return
     */
    @RequestMapping("/personal/info")
    public ResultData personalInfo(@RequestAttribute("systemUser") SystemUserVO systemUserVO){
        System.out.print("================"+systemUserVO.getSystemUserId()+"============");
        PersonalVO personalVO = personalCenterService.personalInfo(systemUserVO.getSystemUserId());
        return ResultData.success(personalVO);
    }
    /**
     * 下载版本
     * @param systemUserId
     * @param response
     */
    @RequestMapping("/download")
    public void  downLoad(Long systemUserId, HttpServletResponse response) {
        try {
            //下载首先是获取服务器地址，而发版的地址放到数据库中
            String url = versionService.selectUrlBySystemUserId(systemUserId);
            ResultData<byte[]> data = fileService.download(url);
            byte[] buf = data.getData();
            // 创建输出流对象
            OutputStream outStream = response.getOutputStream();
            // 开始输出
            outStream.write(buf, 0, buf.length);
            // 关闭流对象
            outStream.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * 发布版本
     * @param systemUserId
     * @param versionNum
     * @param file
     */
    @RequestMapping("/release")
    public void release(Long systemUserId, String versionNum, MultipartFile file){
        versionService.release(systemUserId,versionNum,file);
    }

    /**
     * 问题详情
     * @param id
     * @return
     */
    @RequestMapping("/commonProblem/show")
    public ResultData selectCommonProblemsById( Long id ){
        CommonProblems commonProblems = commonProblemsService.selectCommonProblemsById(id);
        return  ResultData.success(commonProblems);
    }

    /**
     * 我的客服
     * @param systemUserVO
     * @return
     */
    @RequestMapping("/myCustomer/show")
    public ResultData myCustomer(@RequestAttribute("systemUser") SystemUserVO systemUserVO){
        CustomerVO customerVO = personalCenterService.myCustomer(systemUserVO.getSystemUserId());
        return ResultData.success(customerVO);
    }
    /**
     * 身份标签添加
     * @param identityTypeId
     */
    @RequestMapping("/identity/insert")
    public void insertIdentity(Integer  identityTypeId ,@RequestAttribute("systemUser") SystemUserVO systemUserVO){
        System.out.print("================"+systemUserVO.getOnlineUserId()+"===============");
          identityTypeService.insertIdentity(identityTypeId,systemUserVO.getOnlineUserId());
    }
    /**
     * 修改密码
     * @param systemUserVO
     * @param oldPwd
     * @param newPwd
     */
    @RequestMapping("/password/update")
    public void updatePersonalPassWord(@RequestAttribute("systemUser") SystemUserVO systemUserVO,String oldPwd,String newPwd){
        Long systemUserId = systemUserVO.getSystemUserId();
        System.out.print("==============================="+systemUserId);
        systemUserService.updatePersonalPassWord(oldPwd,newPwd,systemUserVO.getSystemUserId());
    }

    /**
     * 修改手机号前校验密码
     * @param systemUserVO
     * @param password
     * @return
     */
    @RequestMapping("/getPassword")
    public ResultData isTruePwd(@RequestAttribute("systemUser") SystemUserVO systemUserVO,String password){
        Long systemUserId = systemUserVO.getSystemUserId();
        System.out.print("==============================="+systemUserId);
         systemUserService.isTruePwd(systemUserVO.getSystemUserId(),password);
         return ResultData.success(true);
    }
    /**
     * 修改手机号获取验证码
     * @param newMobile
     */
    @RequestMapping("/smsCode/show")
    public ResultData getPersonalCode(String newMobile){
       String smsCode =  systemUserService.getSmsCode(newMobile);
        return ResultData.success(smsCode);
    }
    /**
     * 修改手机号
     * @param systemUserVO
     * @param newMobile
     */
    @RequestMapping("/mobile/update")
    public void updatePersonalMobile(@RequestAttribute("systemUser") SystemUserVO systemUserVO,String newMobile,String smsCode){
        systemUserService.updatePersonalMobile(systemUserVO.getSystemUserId(),newMobile,smsCode);
    }

    /**
     * 交易记录列表
     * @param businessTypeId
     * @param startTime
     * @param endTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/trade/list")
    public ResultData tradeList( Long businessTypeId,Date startTime, Date endTime,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize){
        log.info("channel : [{}]   ;   startTime : [{}]  ; endTime : [{}]  ; pageNum : [{}];pageSize :[{}]",businessTypeId,startTime,endTime,pageNum,pageSize);
        PageInfo<List<tradeVO>> pageInfo = personalCenterService.tradeList( businessTypeId,startTime,endTime,pageNum,pageSize);
        return  ResultData.success(pageInfo);
    }

    /**
     * 交易详情
     * @param orderNo
     * @return
     */
    @RequestMapping("/trade/info")
    public ResultData tradeInfo(String orderNo){
       TradeInfoVO tradeInfoVO = personalCenterService.tradeInfo(orderNo);
        return ResultData.success(tradeInfoVO);
    }

    /**
     * 交易分析
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("/trade/analysis")
    public ResultData tradeAnalysis(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime){
       log.info("startTime : [{}] ;  endTime : [{}]", DateUtil.str2Date(startTime,"yyyyMMddHHmmss"), DateUtil.str2Date(endTime,"yyyyMMddHHmmss"));
        TradeAnalysisVO tradeAnalysisVO = personalCenterService.tradeAnalysis(DateUtil.str2Date(startTime,"yyyyMMddHHmmss"),
                DateUtil.str2Date(endTime,"yyyyMMddHHmmss"));
      //  return  ResultData.success(tradeAnalysisVO);
        return null;
    }

}
