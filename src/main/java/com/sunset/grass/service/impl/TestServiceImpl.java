package com.sunset.grass.service.impl;

import com.sunset.grass.dao.TestDao;
import com.sunset.grass.entity.User;
import com.sunset.grass.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestDao testDao;
    @Override
    public User testDemo(String id) {
        return testDao.queryUserMsg(id);
    }

    @Override
    public List<User> queryUserInfos() {
        return testDao.queryUserMsgs();
    }

    @Override
    public void insertUserInfo(User user) {
        testDao.insertUserInfo(user);
        return;
    }
}
