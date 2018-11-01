package com.snxy.business.service;

import com.snxy.business.domain.VegetableImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VegetableImageService {
    void insertVegetableImageList(@Param("vegetableImageList") List<VegetableImage> vegetableImageList);
    List<VegetableImage> selectByOderId(Long deliveryOrderId);
    List<String > searchImages(long orderId);
    void updateByAgent(VegetableImage vegetableImage);
}
