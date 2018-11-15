package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Door {
    private Long id;

    private String doorName;

    private Double longitude;

    private Double latitude;

    private Byte isDelete;

}