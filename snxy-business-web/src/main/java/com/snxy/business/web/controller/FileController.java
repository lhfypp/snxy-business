package com.snxy.business.web.controller;

import com.snxy.business.service.FileOperationService;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.CheckUtil;
import com.snxy.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 24398 on 2018/9/3.
 */
@Controller
public class FileController {

    @Resource
    private FileOperationService fileOperationService;

    @RequestMapping("/file/upload/test")
    @ResponseBody
    public ResultData<String> uploadTest(MultipartFile file){
        String filePath = this.fileOperationService.upload(file);
        return ResultData.success(filePath);
    }


    @RequestMapping("/file/delete")
    @ResponseBody
    public ResultData  delete(@RequestParam("filePath") String filePath){
        CheckUtil.isTrue(StringUtil.isNotBlank(filePath),"图片路径不能为空");
        this.fileOperationService.delete(filePath);
        return ResultData.success("");
    }


    @RequestMapping("/file/download")
    @ResponseBody
    public void download(String filePath, HttpServletResponse response){
       if(filePath == null){
           throw new BizException("图片路径不能为空");
       }
       byte[] bytes = this.fileOperationService.download(filePath);

        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {

        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
