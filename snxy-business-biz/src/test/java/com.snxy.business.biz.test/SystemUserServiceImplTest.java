package com.snxy.business.biz.test;

import com.snxy.business.dao.mapper.SystemUserMapper;
import com.snxy.business.domain.SystemUser;
import com.snxy.business.domain.UserMobileCode;
import com.snxy.common.exception.BizException;
import com.snxy.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemUserServiceImplTest {
    @Resource
    private SystemUserMapper systemUserMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private final String SMS_CODE = "123456";

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void modifyName() {
        String name = "赵六";
        if (StringUtil.isBlank(name)) {
            throw new BizException("要修改的姓名为空");
        }
        SystemUser systemUser = new SystemUser();
        systemUser.setId(1L);
        systemUser.setChineseName(name);
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
    }

    //调用外部接口，获取短信验证码
    public String getSmsCode(Long userId){
        String mobile = systemUserMapper.selectMobileById(userId);
        systemUserMapper.selectMobileById(userId);
        /*
         * 调用接口，获取验证码
         *
         * */
        return SMS_CODE;
    }

    @Test
    public void smsCode() {
        Long userId = 1L;
        String newMobile = "";
        if(StringUtil.isBlank(newMobile)){
            throw new BizException("没有手机号");
        }
        if(!StringUtil.checkMobile(newMobile)){
            log.error("手机号格式错误: [{}]",newMobile);
            throw new BizException("手机号格式错误");
        }
        String smsCode = this.getSmsCode(userId);

        UserMobileCode userMobileCode = new UserMobileCode();
        userMobileCode.setMobile(newMobile);
        userMobileCode.setSmsCode(smsCode);
        //存储在redis中
        String redisKey = userId.toString();
        redisTemplate.opsForValue().set(redisKey+"smsCode",userMobileCode,5, TimeUnit.MINUTES);

    }
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void modifyPhone() {
        Long userId = 1L;
        String smsCode = "123456";
        if(StringUtil.isBlank(smsCode)){
            throw new BizException("验证码为空");
        }
        UserMobileCode userMobileCode = (UserMobileCode) redisTemplate.opsForValue().get(userId.toString()+"smsCode");
        if (userMobileCode.getSmsCode().equals(smsCode)){
            SystemUser systemUser = new SystemUser();
            systemUser.setId(userId);
            systemUser.setMobile(userMobileCode.getMobile());
            systemUserMapper.updateByPrimaryKeySelective(systemUser);
        }else {
            log.error("验证码输入错误:[{}]",smsCode);
            throw new BizException("验证码输入错误");
        }
    }
}
