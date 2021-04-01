package com.sunset.grass.web.controller;


import com.sunset.grass.entity.User;
import com.sunset.grass.service.TestService;
import com.sunset.grass.web.form.test.TestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "测试类")
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @ApiOperation(value = "查询mysql密码",notes = "通过ID查询对应用户名及密码")
    @RequestMapping(value = "/grass.test",method = RequestMethod.GET)
    public User test(TestForm form){
        return testService.testDemo(form.getId());
    }

    @ApiOperation(value = "查询所有账户密码",notes = "查询所有账户密码")
    @RequestMapping(value = "queryUserInfos",method = RequestMethod.POST)
    public List<User> queryUserInfos(){
        return testService.queryUserInfos();
    }

    @ApiOperation(value = "保存新的账户密码",notes = "保存新的账户密码")
    @RequestMapping(value = "insertUserInfo",method = RequestMethod.POST)
    public String insertUserInfo(User user){
        return String.valueOf(testService.insertUserInfo(user));
    }

    @ApiOperation(value = "修改密码",notes = "修改密码")
    @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    public String updateUserInfo(User user){
        return testService.modifyPassword(user);
    }


}
