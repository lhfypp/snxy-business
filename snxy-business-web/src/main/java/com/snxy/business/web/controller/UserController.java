package com.snxy.business.web.controller;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.service.UserImageService;
import com.snxy.business.service.VersionService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserImageService userImageService;
    @Resource
    private FileService fileService;
    @Resource
    private VersionService versionService;

    /**
     * 上传用户头像
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

