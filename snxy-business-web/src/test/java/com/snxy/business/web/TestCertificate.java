package com.snxy.business.web;

import com.alibaba.fastjson.JSON;
import com.snxy.business.service.vo.CertificateVo;

public class TestCertificate {
    public static void main(String[] args) {
        CertificateVo certificateVo = CertificateVo.builder()
                .orderNo(1L)
                .qualitied(1)
                .build();
        String s = JSON.toJSONString(certificateVo);
        System.out.println(s);
    }
}
