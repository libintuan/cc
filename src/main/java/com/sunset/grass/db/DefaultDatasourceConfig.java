package com.sunset.grass.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.JSON;
import com.sunset.grass.util.LoggerHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@MapperScan(basePackages="com.sunset.grass.dao",sqlSessionFactoryRef = "sqlSessionFactory")
public class DefaultDatasourceConfig {

    @Bean(name = "datasource")
    public DruidDataSource getDatasource(){
        System.out.println("defaultDatasource配置文件加载开始###########");
        LoggerHelper.outputObj("defaultDatasource配置文件加载开始。。。。。。。");
        DruidDataSource druidDataSource=new DruidDataSource();
        Properties properties=new Properties();
        InputStream in=ClassLoader.getSystemResourceAsStream("defaultDatasource.properties");
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
        LoggerHelper.outputObj("defaultDatasource配置文件"+properties);
        System.out.println("defaultDatasource配置文件"+ JSON.toJSONString(properties));
//        druidDataSource.setConnectProperties(properties);

        System.out.println(druidDataSource.getUsername());
        return druidDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DruidDataSource datasource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mybatis/mapper/*.xml"));
        Resource resource= new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

//    @Bean
//   public MapperScannerConfigurer defaultMapperScannerConfigurer(){
//        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("com.sunset.grass.dao");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("datasource");
//        return mapperScannerConfigurer;
//
//    }


}
