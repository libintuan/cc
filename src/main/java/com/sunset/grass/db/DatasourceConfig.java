package com.sunset.grass.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonString;
import com.sunset.grass.util.LoggerHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

@Configuration
@MapperScan(basePackages="com.sunset.grass.dao2",sqlSessionFactoryRef = "sqlSessionFactory2")
public class DatasourceConfig {

    @Bean(name = "datasource2")
    public DruidDataSource getDatasource2(){
        System.out.println("data2配置文件加载开始###########");
        LoggerHelper.outputObj("data2配置文件加载开始。。。。。。。");
        DruidDataSource druidDataSource=new DruidDataSource();
        Properties properties=new Properties();
        InputStream in=ClassLoader.getSystemResourceAsStream("datasource2.properties");
//        properties.getProperty("classpath:/resources/datasource2.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoggerHelper.outputObj("data2配置文件"+properties);
        System.out.println("data2配置文件"+ JSON.toJSONString(properties));
//        druidDataSource.setConnectProperties(properties);

        System.out.println(druidDataSource.getUsername());
        return druidDataSource;
    }

    @Bean("sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory(DruidDataSource datasource2) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource2);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mybatis/mapper2/*.xml"));
        Resource resource= new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

//    @Bean
//    @ConfigurationProperties(prefix = "mybatis.configuration")
//    public org.apache.ibatis.session.Configuration globalConfiguration(){
//        return new org.apache.ibatis.session.Configuration();
//    }

//    @Bean
//    public MapperScannerConfigurer apperScannerConfigurer2(){
//        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("com.sunset.grass.dao2");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("datasource2");
//        return mapperScannerConfigurer;
//
//    }

}
