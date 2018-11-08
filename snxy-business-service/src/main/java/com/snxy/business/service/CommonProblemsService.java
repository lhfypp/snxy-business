package com.snxy.business.service;

import com.snxy.business.domain.CommonProblems;

import java.util.List;

public interface CommonProblemsService {
    List<CommonProblems> selectAllCommonProblems();
    CommonProblems selectCommonProblemsById(Long id);
}
