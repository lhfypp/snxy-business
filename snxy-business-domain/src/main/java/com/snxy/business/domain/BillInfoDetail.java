package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillInfoDetail {
    private DeliveryOrder deliveryOrder ;//改
    private DriverPartInfo driverPartInfo;
    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    private List<Goods>goods;
    private List<GPSLocation>gPSLocations;
    private List<Valication>valications;
    private List<String>images;
}
