package com.sunset.grass.service;

import com.sunset.grass.entity.User;

import java.util.List;

public interface TestService {
    User testDemo(String id);
    List<User> queryUserInfos();
    void insertUserInfo(User user);
}
