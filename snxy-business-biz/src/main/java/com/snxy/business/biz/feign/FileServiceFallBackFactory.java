package com.snxy.business.biz.feign;

import com.snxy.common.response.ResultData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 24398 on 2018/9/19.
 */

@Component
public class FileServiceFallBackFactory implements FileService{

    private static final String msg = "文件服务";


    @Override
    public ResultData<String> upload(MultipartFile file) {
        return ResultData.fail(msg + "： 上传文件失败，服务降级");
    }

    @Override
    public ResultData delete(String filePath) {
        return ResultData.fail(msg + "： 删除文件失败，服务降级");
    }

    @Override
    public ResultData deleteBatch(List<String> filePaths) {
        return ResultData.fail(msg + "： 批量删除文件失败，服务降级");
    }

    @Override
    public ResultData<byte[]> download(String filePath) {  return ResultData.fail(msg + "： 下载文件失败，服务降级");}
}
