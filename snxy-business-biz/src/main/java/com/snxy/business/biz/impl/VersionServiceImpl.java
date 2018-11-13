package com.snxy.business.biz.impl;

import com.snxy.business.biz.feign.FileService;
import com.snxy.business.dao.mapper.VersionMapper;
import com.snxy.business.service.VersionService;
import com.snxy.common.exception.BizException;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
@Slf4j
public class VersionServiceImpl implements VersionService {

    @Resource
    private FileService fileService;
    @Resource
    private VersionMapper versionMapper;

    @Override
    public void release(Long systemUserId,String versionNum, MultipartFile file) {

        ResultData<String> resultData = fileService.upload(file);

        if (!resultData.isResult()){
            throw new BizException("=================上传版本失败");
        }
       //成功后，获取服务器上的地址
        String url = resultData.getData();
        versionMapper.insertRelease(systemUserId,versionNum,url);
    }

    @Override
    public String selectUrlBySystemUserId(Long systemUserId) {
        return versionMapper.selectUrlBySystemUserId(systemUserId);
    }
}
