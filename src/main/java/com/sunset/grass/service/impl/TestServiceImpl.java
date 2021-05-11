package com.sunset.grass.service.impl;

import com.sunset.grass.anno.SysLog;
import com.sunset.grass.dao.TestDao;
import com.sunset.grass.entity.User;
import com.sunset.grass.service.AbstractCommonService;
import com.sunset.grass.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl extends AbstractCommonService implements TestService{
    @Autowired
    TestDao testDao;

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

    @Override
    public int insertUserInfo(User user) {
        testDao.insertUserInfo(user);
        return testDao.insertUserInfo(user);
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
    private void checkForm(User user){
        if (user==null||user.getName()==null||user.getPassword()==null){
            LOGGER.warn("入参信息异常");
            throw new RuntimeException("入参检验失败，请重新提单");
        }
    }
}
