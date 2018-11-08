package com.snxy.business.biz;

import com.snxy.business.domain.CommonProblems;
import com.snxy.business.service.CommonProblemsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GetAllCommonProblemsTests {
    @Resource
    private CommonProblemsService commonProblemsService;

    /**
     * 获取所有常见问题
     */
    @Test
    public void getAllCommonProblems(){
        List<CommonProblems> problemsList = commonProblemsService.selectAllCommonProblems();
        for (int i=0;i<problemsList.size();i++){
            String question = problemsList.get(i).getQuestion();
            System.out.print("==========================>>"+question);
        }
    }
    /**
     * 获取单个问题详情
     */
    @Test
    public void getCommonProblemById(){
        CommonProblems commonProblems = commonProblemsService.selectCommonProblemsById(1L);
        System.out.print("=====================>>"+commonProblems);
    }
}
