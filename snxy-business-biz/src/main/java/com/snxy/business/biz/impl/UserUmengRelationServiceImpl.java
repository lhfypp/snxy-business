package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.UserUmengRelationMapper;
import com.snxy.business.domain.UserUmengRelation;
import com.snxy.business.service.UserUmengRelationService;
import com.snxy.common.exception.BizException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zhangfeng233
 * @Date: 2018/11/16 14:58
 */
@Service
public class UserUmengRelationServiceImpl implements UserUmengRelationService {
    @Resource
    UserUmengRelationMapper userUmengRelationMapper;

    @Override
    public void insertSelective(UserUmengRelation record) {
        UserUmengRelation userUmengRelation = this.userUmengRelationMapper.selectBySystemUserId(record.getSystemUserId());
        if(userUmengRelation == null){
            int i = this.userUmengRelationMapper.insertSelective(record);
            if(i<1){
                throw new BizException("添加失败");
            }
        }
        if(!userUmengRelation.getDeviceToken().equals(record.getDeviceToken())){
            record.setId(userUmengRelation.getId());
            int i = this.userUmengRelationMapper.updateByPrimaryKeySelective(record);
            if(i<1){
                throw new BizException("添加失败");
            }
        }

    }

    @Override
    public UserUmengRelation selectBySystemUserId(Long systemUserId) {
        UserUmengRelation userUmengRelation = this.userUmengRelationMapper.selectBySystemUserId(systemUserId);
        return userUmengRelation;
    }

    @Override
    public List<UserUmengRelation> selectBySystemUserIdList(List<Long> systemUserIdList) {
        List<UserUmengRelation> userUmengRelationList = userUmengRelationMapper.selectBySystemUserIdList(systemUserIdList);
        return userUmengRelationList;
    }
}
