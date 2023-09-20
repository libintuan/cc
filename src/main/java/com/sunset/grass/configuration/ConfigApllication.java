package com.sunset.grass.configuration;

import com.grass.sdk.anntation.Sdk;
import com.sunset.grass.entity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@Sdk
public class ConfigApllication implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    @Autowired
    private List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers;

    @Bean
    public User user(){
        return new User();
    }
    @Bean
    public CustomerHandlerMethodReturnValueHandler customerHandlerMethodReturnValueHandler(){
        System.out.println("ggggggggggggggggggggg+"+user());
        return new CustomerHandlerMethodReturnValueHandler();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> handlers=Stream.of(handlerMethodReturnValueHandlers,requestMappingHandlerAdapter.getReturnValueHandlers()).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
        requestMappingHandlerAdapter.setReturnValueHandlers(handlers);
    }
}

