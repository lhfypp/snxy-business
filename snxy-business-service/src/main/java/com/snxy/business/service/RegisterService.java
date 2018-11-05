package com.snxy.business.service;

import com.snxy.common.response.ResultData;

public interface RegisterService {

    String chooseIdentity(Long systemUserId, String name, Integer identityId);
}
