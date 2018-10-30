package com.snxy.business.service;

import com.snxy.business.domain.VegetableImage;

import java.util.List;

public interface VegetableImageService {
void insertVegetableImageList(List<VegetableImage> vegetableImageList);
    List<VegetableImage> selectByOderId(Long deliveryOrderId);
    List<String > searchImages(long orderId);
    void updateByAgent(VegetableImage vegetableImage);
}
