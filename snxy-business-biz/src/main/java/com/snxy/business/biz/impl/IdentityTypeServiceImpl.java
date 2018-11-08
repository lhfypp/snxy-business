package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.IdentityTypeMapper;
import com.snxy.business.domain.IdentityType;
import com.snxy.business.service.IdentityTypeService;
import com.snxy.business.service.vo.IdentityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IdentityTypeServiceImpl implements IdentityTypeService {

    @Resource
    private IdentityTypeMapper identityTypeMapper;

    @Override
    public List<IdentityVO> selectAllIdentity() {
        List<IdentityType> identityTypeList = identityTypeMapper.selectAllType();
        List<IdentityVO> identityVOList = identityTypeList.parallelStream().map(s -> IdentityVO.builder()
                       .identityName(s.getIdentityName())
                       .id(s.getId())
                       .build())
                       .collect(Collectors.toList());

//        List<IdentityVO> identityVOList = new ArrayList<>();
//        for (int i = 0; i < identityTypeList.size(); i++) {
//            IdentityVO identityVO = new IdentityVO();
//            BeanUtils.copyProperties(identityTypeList.get(i),identityVO);
//            identityVOList.add(identityVO);
//        }
        return identityVOList;
    }

    @Override
    public List<IdentityType> listAll(boolean b) {
        return null;
    }
}
