package com.snxy.business.biz.feign;

import com.snxy.business.biz.config.FeignMultipartSupportConfig;
import com.snxy.common.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 24398 on 2018/9/19.
 */

@FeignClient(name="snxy-file-service",fallbackFactory = FileServiceFallBackFactory.class,
         configuration = FeignMultipartSupportConfig.class)
public interface FileService {

    /***
     * 上传单个文件
     * @param file
     * @return
     */
      @PostMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      ResultData<String> upload(@RequestPart("file")MultipartFile file);

    /**
     * 删除单个文件
     * @param filePath
     * @return
     */
      @PostMapping("/file/delete")
      ResultData   delete(@RequestParam("filePath") String filePath);

    /***
     * 批量删除文件
     * @param filePaths
     * @return
     */
      @PostMapping("/file/delete/batch")
      ResultData   deleteBatch(@RequestBody List<String> filePaths);

    /**
     * 下载显示图片
     * @param filePath
     * @return
     */
      @RequestMapping("/file/download")
      ResultData<byte[]> download(@RequestParam("filePath") String filePath);

}
