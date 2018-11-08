package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.CommonProblemsMapper;
import com.snxy.business.domain.CommonProblems;
import com.snxy.business.service.CommonProblemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CommonProblemsServiceImpl implements CommonProblemsService {
    @Resource
    private CommonProblemsMapper commonProblemsMapper;

    /**
     * 获取常见问题列表
     * @return
     */
    @Override
    public List<CommonProblems> selectAllCommonProblems() {
       List<CommonProblems> problemsList =  commonProblemsMapper.selectAllCommonProblems();
        return problemsList;
    }

    /**
     * 获取问题详情
     * @param id
     * @return
     */
    @Override
    public CommonProblems selectCommonProblemsById(Long id) {
        CommonProblems commonProblems = commonProblemsMapper.selectByPrimaryKey(id);
        return commonProblems;
    }
}
