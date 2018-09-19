package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.service.FileOperationService;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 24398 on 2018/9/19.
 */

@Component
@Slf4j
public class FileOperationServiceImpl implements FileOperationService {

    @Resource
    private FileService fileService;


    @Override
    public String upload(MultipartFile file) {
        ResultData<String> resultData = this.fileService.upload(file);
        String filePath = null;
        if(resultData.isResult()){
            filePath = resultData.getData();
        }else{
            System.out.println("文件上传失败 ： --------------------" + resultData.getMsg());
        }
        return filePath;
    }



    @Override
    public void delete(String filePath) {
        ResultData resultData =  this.fileService.delete(filePath);
        if(resultData == null || !resultData.isResult()){
            log.error("删除图片失败 : 错误信息 ： [{}]",resultData.getMsg());
             throw new BizException("删除图片失败 "+resultData.getMsg());
        }

    }

    public byte[] download(String filePath){
        ResultData<byte[]> resultData = this.fileService.download(filePath);
        byte[] bytes = null;
        if(resultData == null || !resultData.isResult()){
            throw new BizException("下载图片失败");
        }
        bytes = resultData.getData();

        return bytes;
    }


}
