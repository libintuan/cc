package com.sunset.grass.web.controller;


import com.sunset.grass.entity.User;
import com.sunset.grass.service.TestService;
import com.sunset.grass.service.excel.FtpJSch;
import com.sunset.grass.util.LoggerHelper;
import com.sunset.grass.web.form.test.BaseForm;
import com.sunset.grass.web.form.test.TestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Api(tags = "测试类")
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private FtpJSch ftpJSch;

    @ApiOperation(value = "查询mysql密码",notes = "通过ID查询对应用户名及密码")
    @RequestMapping(value = "/grass.test",method = RequestMethod.GET)
    public User test(TestForm form){
        return testService.testDemo(form.getId());
    }

    @ApiOperation(value = "查询所有账户密码",notes = "查询所有账户密码")
    @RequestMapping(value = "queryUserInfos",method = RequestMethod.POST)
    public List<User> queryUserInfos(BaseForm form){
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

    @ApiOperation(value = "测试escel",notes = "excel")
    @RequestMapping(value = "testExcel",method = RequestMethod.POST)
    public List<List<String>> testExcel(User user){
        List<String> list=new ArrayList<>();
        Collection<String> cc= Collections.unmodifiableCollection(list);
        cc.add("bb");
//        ExtelRead.writeExcel();
//        ftpUtil.uploadToFtp(new File("/Users/tuantuan/IdeaProjects/grass/6666.xlsx"));
        /*ftpJSch.upload("/Users/tuantuan/IdeaProjects/grass/6666.xlsx");
        ftpJSch.download("");
        return ExtelRead.testRead();*/
        return null;
    }

    public static void main1(String[] args) {
        /*List<String> list=new ArrayList<>();
        list.add("aaa");
        list.add("bbbb");
        Collection<String> cc= Collections.unmodifiableCollection(list);
        list.add("cc");
        System.out.println(LoggerHelper.outputObj(cc));
        Iterator<String> iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            list.add("dd");
            list.remove(3);
        }*/
        Class clazz=null;
        Field field=null;
        try {
            clazz=Class.forName("com.sunset.grass.entity.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor<User> constructor=null;
        try {
            constructor=clazz.getConstructor(String.class,String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            User user=constructor.newInstance("wahaha","12345");
            System.out.println(LoggerHelper.outputObj(user));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        ExtelRead.writeExcel();
//        ftpUtil.uploadToFtp(new File("/Users/tuantuan/IdeaProjects/grass/6666.xlsx"));
        /*ftpJSch.upload("/Users/tuantuan/IdeaProjects/grass/6666.xlsx");
        ftpJSch.download("");
        return ExtelRead.testRead();*/
    }

    public static void main(String[] args) {
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        String aa="1.11";
        String bb="1.8999999";
        System.out.println(decimalFormat.format(Double.valueOf(aa)));
        System.out.println(decimalFormat.format(Double.valueOf(bb)));
        ReentrantLock lock=new ReentrantLock();
        lock.lock();
        lock.tryLock();
        lock.isFair();
    }



}
