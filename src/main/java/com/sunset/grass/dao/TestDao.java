package com.sunset.grass.dao;

import com.sunset.grass.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {
    User queryUserMsg(String id);
    List<User> queryUserMsgs();
    int insertUserInfo(User user);

}
