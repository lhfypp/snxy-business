package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VegetableCategoryMapper;
import com.snxy.business.domain.VegetableCategory;
import com.snxy.business.service.VegetableCategoryService;
import com.snxy.business.service.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VegetableCategoryServiceImpl implements VegetableCategoryService {
    @Resource
    private VegetableCategoryMapper vegetableCategoryMapper;

    @Override
    public List<CategoryVO> selectAllCategory() {
        //查询所有类
        List<VegetableCategory> vegetableCategoryList = vegetableCategoryMapper.selectAllCategory();
        if(vegetableCategoryList == null || vegetableCategoryList.size() == 0){
            return null;
        }

        List<CategoryVO> categoryVOList = vegetableCategoryList.parallelStream().map(s -> CategoryVO.builder()
                                        .parentId(s.getParentId())
                                        .id(s.getId())
                                        .vegetableCategoryName(s.getVegetableCategoryName())
                                        .build())
                                        .collect(Collectors.toList());
        List<CategoryVO> categoryVOS = new ArrayList<>();
        categoryVOList.forEach(sourceCategory -> {
            if(sourceCategory.getParentId()==null||sourceCategory.getParentId().equals("")){
                categoryVOS.add(sourceCategory);
            }
            categoryVOList.forEach(childCategory -> {
                if(childCategory.getParentId()!=null){
                    if(sourceCategory.getId()==childCategory.getParentId()){
                        sourceCategory.getChildCategory().add(childCategory);
                    }
                }
            });
        });


        return categoryVOS;
    }


}
