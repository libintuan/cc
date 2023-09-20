package com.sunset.grass.registor;

import com.sunset.grass.aspect.ControllerAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ImportBeanGrassDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    public static final String ASPECT_001="aspect_controller";
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registryAspect(registry);
    }

    void registryAspect(BeanDefinitionRegistry beanDefinitionRegistry){
        if (beanDefinitionRegistry.isBeanNameInUse(ASPECT_001)) {
            logger.warn(ASPECT_001+"此bean已经注册XXXXXXXXXXXXXXXXXXXXXXXX");
            return;
        }
        RootBeanDefinition rootBeanDefinition=new RootBeanDefinition(ControllerAspect.class);
        beanDefinitionRegistry.registerBeanDefinition(ASPECT_001,rootBeanDefinition);
        logger.info(ASPECT_001+"注册成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
    }
}
