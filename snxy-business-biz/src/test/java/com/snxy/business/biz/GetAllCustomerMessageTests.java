package com.snxy.business.biz;

import com.snxy.business.domain.CustomerMessage;
import com.snxy.business.service.CustomerMessageService;
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
public class GetAllCustomerMessageTests {

    @Resource
    private CustomerMessageService customerMessageService;

    /**
     * 获取留言列表
     */
    @Test
    public void GetAllCustomerMessageService(){
        List<CustomerMessage> customerMessages = customerMessageService.selectAllCustomerMessage();
        for (int i=0;i<customerMessages.size();i++){
            String content = customerMessages.get(i).getContent();
            String messageAnswer = customerMessages.get(i).getMessageAnswer();
            System.out.print("====================>>>"+content);
            System.out.print("====================>>>"+messageAnswer);

        }
    }
}
