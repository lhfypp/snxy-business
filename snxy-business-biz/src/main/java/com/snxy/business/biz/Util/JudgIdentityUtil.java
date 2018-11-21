package com.snxy.business.biz.util;

import com.snxy.business.domain.IdentityType;
import com.snxy.business.service.vo.IdentityVO;
import com.snxy.business.service.vo.SystemUserVO;

import java.util.List;

public class JudgIdentityUtil {
    public static String  judgIdentity(SystemUserVO systemUserVo){
        // 1商户负责人，2商户员工，3司机 4，随便看看
        List<IdentityVO> identityVOList=systemUserVo.getIdentityTypes();
        StringBuffer identityStr=new StringBuffer();
        for(IdentityVO IdentityVO:identityVOList){
            if("商户负责人".equals(IdentityVO.getIdentityName().trim())){
                identityStr.append("1");
            }else if("商户员工".equals(IdentityVO.getIdentityName().trim())){
                identityStr.append("2");
            }else if("司机".equals(IdentityVO.getIdentityName().trim())){
                identityStr.append("3");
            }else if("随便看看".equals(IdentityVO.getIdentityName().trim())){
                identityStr.append("4");
            }

        }
        return identityStr.toString();


    }
}
