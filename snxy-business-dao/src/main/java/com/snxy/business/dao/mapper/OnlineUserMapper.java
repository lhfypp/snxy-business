package com.snxy.business.dao.mapper;

import com.snxy.business.domain.OnlineUser;
<<<<<<< HEAD
=======
import org.apache.ibatis.annotations.Param;

>>>>>>> a779b14a370bb8d9e396182b9f02614933957511

public interface OnlineUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OnlineUser record);

    int insertSelective(OnlineUser record);

    OnlineUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OnlineUser record);

    int updateByPrimaryKey(OnlineUser record);

<<<<<<< HEAD
    Long selectOnlineIdBySystemId(Long systemUserId);

    void updateNameById(Long onlineUserId, String userName);
=======
    void updateNameBySystemUserId(@Param("systemUserId") Long systemUserId, @Param("name") String name);

    Long selectOnlineUserIdBySystemUserId(Long systemUserId);
>>>>>>> a779b14a370bb8d9e396182b9f02614933957511
}