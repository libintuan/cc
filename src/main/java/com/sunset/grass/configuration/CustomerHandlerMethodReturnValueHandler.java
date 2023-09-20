package com.sunset.grass.configuration;

import com.sunset.grass.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomerHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private User user;
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        System.out.println("hahahahahhahahah######################################");
        return false;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {

        System.out.println("dddddddddddddddddddddddddddddddddd+++++++++++++++++++++++++++++++++=");
    }
}
