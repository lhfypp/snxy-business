package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPSLocation {
    private Float longitude;

    private Float latitude;

    private Float height;
    private Date collectionTime;
}
