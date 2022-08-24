package com.sunset.grass.service.impl;

import com.sunset.grass.anno.SysLog;
import com.sunset.grass.dao.TestDao;
import com.sunset.grass.dao2.UserMapper;
import com.sunset.grass.entity.User;
import com.sunset.grass.entity.UserInfo;
import com.sunset.grass.service.AbstractCommonService;
import com.sunset.grass.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestServiceImpl extends AbstractCommonService implements TestService{
    @Autowired
    TestDao testDao;
    @Autowired
    UserMapper userMapper;

    @SysLog("查询单个密码")
    @Override
    public User testDemo(String id) {
        return testDao.queryUserMsg(id);
    }

    @SysLog("所有密码查询")
    @Override
    public List<User> queryUserInfos() {

        return testDao.queryUserMsgs();
    }

    @Transactional
    @Override
    public int insertUserInfo(User user) {
       int i= testDao.insertUserInfo(user);
       if (i>=0){
           LOGGER.warn("eeeessssssaaaaaaa");
           throw new RuntimeException("事务回滚！！！！！");
       }
        return i;
    }

    @SysLog("密码修改")
    @Override
    public String modifyPassword(User user) {
        this.checkForm(user);
        User queryUserInfo=testDao.queryUserMsg(user.getId());
        if (StringUtils.equals(user.getName(),queryUserInfo.getName())){
            if (StringUtils.isNotBlank(queryUserInfo.getPassword())&&!StringUtils.equals(user.getLastPassword(),queryUserInfo.getPassword())){
                LOGGER.warn("新旧密码不一致");
                throw new RuntimeException("新旧密码不一致，请重新输入");
            }
            int numb=testDao.updateUserInfo(user);
            if (numb==1){
                return "sucess";
            }
            return "fail";
        }else {
            LOGGER.warn("账户名不匹配");
            throw new RuntimeException("账户名不匹配");
        }
    }

    @Override
    public String testSentiel() {

        return "通过通过通过！！！！";
    }

    private void checkForm(User user){
        if (user==null||user.getName()==null||user.getPassword()==null){
            LOGGER.warn("入参信息异常");
            throw new RuntimeException("入参检验失败，请重新提单");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String testJTA() {
        UserInfo userInfo=new UserInfo();
        userInfo.setName("奥特蛋2");
        userMapper.save(userInfo);
        User user=new User();
        user.setName("测试数据2");
        testDao.insertUserInfo(user);
        if (true){
            throw new RuntimeException("测试异常");
        }
        return "sucess";
    }
}
