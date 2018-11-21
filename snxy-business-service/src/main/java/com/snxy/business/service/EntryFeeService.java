package com.snxy.business.service;

import com.snxy.business.domain.EntryFee;

import java.util.List;

public interface EntryFeeService {
    EntryFee selectFeeByOrderNo(String orderNo);

}
