package com.snxy.business.biz.feign;

<<<<<<< HEAD
import com.snxy.business.service.vo.SystemUserVO;
import com.snxy.common.response.ResultData;
=======
>>>>>>> 0715307c3886f405043faeee6e33e8c66c8ff20e

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAgentServiceFallBackFactory implements UserAgentService {
    @Override
<<<<<<< HEAD
    public ResultData<SystemUserVO> login(LoginUserVO loginUserVO) {
        return ResultData.fail("登陆失败 ："+msg);
=======
    public void refresh(Long systemUserId) {
        log.error("刷新用户缓存信息失败");
>>>>>>> 0715307c3886f405043faeee6e33e8c66c8ff20e
    }
}
