package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableImageMapper;
import com.snxy.business.domain.VegetableImage;
import com.snxy.business.service.VegetableImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class VegetableImageServiceImpl implements VegetableImageService {
    @Resource
    private VegetableImageMapper vegetableImageMapper;
    @Override
    public List<String> searchImages(long orderId) {
        return vegetableImageMapper.SelectListByOderId(orderId);
    }
	 @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertVegetableImageList(List<VegetableImage> vegetableImageList) {
        vegetableImageMapper.insertVegetableImageList(vegetableImageList);
    }

    @Override
    public List<VegetableImage> selectByOderId(Long deliveryOrderId) {
        List<VegetableImage> vegetableImageList = vegetableImageMapper.selectByOderId(deliveryOrderId);
        return vegetableImageList;
    }
}
