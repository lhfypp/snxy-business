package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.UserIdentityMapper;
import com.snxy.business.domain.Identy;
import com.snxy.business.domain.UserIdentity;
import com.snxy.business.service.UserIdentityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserIdentityServiceImpl implements UserIdentityService {

    @Resource
    private UserIdentityMapper userIdentityMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setUserIdentity(Long onlineUserId,List<Integer> identities) {
        // 先删除之前的身份类型
        this.userIdentityMapper.deleteIdentity(onlineUserId);
        // 添加新身份类型
        List<UserIdentity> userIdentities = identities.parallelStream().map(identity ->
                                              UserIdentity.builder()
                                                          .identityId(identity)
                                                          .onlineUserId(onlineUserId)
                                                          .isDelete((byte)1)
                                                          .build())
                                                     .collect(Collectors.toList());

        if(!userIdentities.isEmpty()){
           this.userIdentityMapper.insertBatch(userIdentities);
        }

    }

    @Override
    public List<Identy> selectUserIdentyByOnlineUserIdList(List<Long> onlineUserIdList) {
        List<Identy> identyList = userIdentityMapper.selectUserIdentyByOnlineUserIdList(onlineUserIdList);
        return identyList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUserIdentyList(List<UserIdentity> userIdentityList) {
        userIdentityMapper.insertBatch(userIdentityList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByOnlineUserId(Long onlineUserId, byte isDelete) {
        userIdentityMapper.updateByOnlineUserId(onlineUserId,isDelete);
    }


}
