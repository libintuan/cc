package com.sunset.grass.configuration;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.garss.sdk.spring.GrassSdk;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

@Configuration
@GrassSdk
//@ComponentScan("com.sunset.grass")
public class ServerConfigration {
    @Bean
    public JtaTransactionManager jtaTransactionManager(){
        UserTransactionManager userTransactionManager=new UserTransactionManager();
        UserTransaction userTransaction=new UserTransactionImp();
        return new JtaTransactionManager(userTransaction,userTransactionManager);
    }

}
