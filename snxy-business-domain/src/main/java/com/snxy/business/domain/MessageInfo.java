package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageInfo {
    private String ticker;//通知栏提示文字
    private String title;//通知栏标题
    private String remark;//通知文字描述
    private Integer moduleType;
    private Long noticeId;
    private Long childId;
    private String noticeDate;
    private List<DeviceInfo> deviceInfos;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceInfo {
        String deviceToken;//设备唯一标识
        String phoneType;//设备类型  安卓  IOS
    }
}
