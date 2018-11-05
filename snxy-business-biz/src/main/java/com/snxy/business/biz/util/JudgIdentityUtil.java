package com.snxy.business.biz.util;

import java.util.List;

public class JudgIdentityUtil {
    public static String  judgIdentity(SystemUserVo systemUserVo){
        // 1代表商户，2代表代办，3代表司机
        List<UserIdentityVo> userIdentityVoList=systemUserVo.getIdentityTypes();
        StringBuffer identityStr=new StringBuffer();
        for(UserIdentityVo userIdentityVo:userIdentityVoList){
            if("商户公司".equals(userIdentityVo.getIdentityName().trim())){
                identityStr.append("1");
            }else if("代办".equals(userIdentityVo.getIdentityName().trim())){
                identityStr.append("2");
            }else if("司机".equals(userIdentityVo.getIdentityName().trim())){
                identityStr.append("3");
            }

        }
        return identityStr.toString();


    }
}
