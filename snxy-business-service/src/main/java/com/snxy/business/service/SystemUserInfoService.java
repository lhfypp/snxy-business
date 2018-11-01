package com.snxy.business.service;



import com.snxy.business.domain.SystemUserInfo;

import java.util.List;

public interface SystemUserInfoService {
    List<String> searchPhones( String onlineUserID);

}
