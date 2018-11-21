package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfo {
    private String deviceToken;//设备唯一标识
    private String phoneType;//设备类型  安卓  IOS
}
