package com.sunset.grass.web.controller;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sunset.grass.dao2.UserMapper;
import com.sunset.grass.entity.User;
import com.sunset.grass.entity.UserInfo;
import com.sunset.grass.service.TestService;
import com.sunset.grass.service.excel.FtpJSch;
import com.sunset.grass.web.form.test.BaseForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Api(tags = "测试类")
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FtpJSch ftpJSch;

    private Logger logger= LoggerFactory.getLogger(this.getClass());




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

    @ApiOperation(value = "查询客户信息",notes = "查询客户信息")
    @RequestMapping(value = "getUserInfo",method = RequestMethod.POST)
    public UserInfo getUserInfo(UserInfo user){
        return userMapper.get(user);
    }

    @ApiOperation(value = "testJTA",notes = "testJTA")
    @RequestMapping(value = "testJTA",method = RequestMethod.POST)
    public String testJTA(UserInfo user){
        return testService.testJTA();
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
    @ApiOperation(value = "测试sential",notes = "测试sential")
    @RequestMapping(value = "testSentiel",method = RequestMethod.POST)
//    public String testSentiel(){
//        return testService.testSentiel();
//    }
    public String testSentiel(){
        Entry entry = null;
        // 资源名
        String resourceName = "2222";
        try {
            // entry可以理解成入口登记
            entry = SphU.entry(resourceName);
            // 被保护的逻辑, 这里为订单查询接口
            return testService.testSentiel();
        } catch (BlockException blockException) {
            // 接口被限流的时候, 会进入到这里
            logger.warn("---getOrder1接口被限流了---, exception: ", blockException);
            return "接口限流, 返回空";
        } finally {
            // SphU.entry(xxx) 需要与 entry.exit() 成对出现,否则会导致调用链记录异常
            if (entry != null) {
                entry.exit();
            }
        }
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
        /*Class clazz=null;
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
        }*/

//        ExtelRead.writeExcel();
//        ftpUtil.uploadToFtp(new File("/Users/tuantuan/IdeaProjects/grass/6666.xlsx"));
        /*ftpJSch.upload("/Users/tuantuan/IdeaProjects/grass/6666.xlsx");
        ftpJSch.download("");
        return ExtelRead.testRead();*/
    }

    public static void main(String[] args) {
        /*DecimalFormat decimalFormat=new DecimalFormat("0.00");
        String aa="1.11";
        String bb="1.8999999";
        System.out.println(decimalFormat.format(Double.valueOf(aa)));
        System.out.println(decimalFormat.format(Double.valueOf(bb)));
        ReentrantLock lock=new ReentrantLock();
        lock.lock();
        lock.tryLock();
        lock.isFair();*/
        /*System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(LocalDate.now().plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(LocalDate.now().plusMonths(2).with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(LocalDate.now().plusMonths(2).with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年M月")));*/
        /*BigDecimal aa=new BigDecimal("0.0505").setScale(2, RoundingMode.DOWN);
        BigDecimal bb=new BigDecimal("2");
        System.out.println(aa.multiply(bb).subtract(aa).toString());
        Thread thread=new Thread();
        thread.start();
        System.gc();*/
        String aa="20220202 ";
        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate ld=LocalDate.parse(aa,df);
    }



}
