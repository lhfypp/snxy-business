package com.snxy.business.service.vo;

import com.snxy.business.domain.CommonProblems;
import com.snxy.business.domain.CustomerMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVO {
    private List<CustomerMessage> customerMessageList;//客服-问题留言
    private String mobile;                            //客服电话
    private List<CommonProblems> commonProblemsList;  //常见问题列表
}
