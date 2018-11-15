package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonProblems {
    private Long id;

    private String question;

    private String answer;

    private String problemDetail;

    private Byte isDelete;

}