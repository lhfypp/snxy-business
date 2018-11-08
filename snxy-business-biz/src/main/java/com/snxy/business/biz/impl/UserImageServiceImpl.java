package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.dao.mapper.UserImageMapper;
import com.snxy.business.service.UserImageService;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;


@Service
@Slf4j
public class UserImageServiceImpl implements UserImageService {

    @Resource
    private UserImageMapper userImageMapper;
    @Resource
    private FileService fileService;

    /**
     * 更换用户头像
     * @param systemUserId
     * @param file
     */
    @Override
    public void updateImageById(Long systemUserId, File file) {
//        调用文件服务上传文件到服务器，返回一个resultData
        ResultData<String> resultData = fileService.upload((MultipartFile) file);
//        判断resultData中result是否为true
        if (resultData.isResult()==false){
            throw new BizException("上传文件失败");
        }
//        如果上传成功，获取resultData中data,相当于图片在服务器保存地址
        String url = resultData.getData();
//        将url保存到userImage表中
        userImageMapper.updateImageById(systemUserId,url);
    }
}
