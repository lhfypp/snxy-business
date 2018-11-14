package com.snxy.business.web.controller;



import com.snxy.business.biz.feign.FileService;
import com.snxy.business.service.OnlineUserService;
import com.snxy.business.service.PerSettingsHomepageService;
import com.snxy.business.service.UserImageService;
import com.snxy.business.service.VersionService;
import com.snxy.business.service.vo.CompanyVO;
import com.snxy.business.service.vo.SystemUserVO;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
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
}
