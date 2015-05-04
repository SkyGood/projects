package com.tianwen.commons;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.uccyou.core.config.DefaultAppConfig;

@Configuration
@ComponentScan(basePackageClasses = AppConfig.class)
@EnableTransactionManagement
@EnableWebMvc
@EnableAspectJAutoProxy
@PropertySource({"classpath:jdbc.properties", "classpath:site.properties"})
public class AppConfig extends DefaultAppConfig {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
//    }

    
}
