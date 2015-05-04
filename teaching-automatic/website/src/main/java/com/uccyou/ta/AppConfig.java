package com.uccyou.ta;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.uccyou.core.config.DefaultAppConfig;
import com.uccyou.ta.commons.interceptor.AdminInterceptor;
import com.uccyou.ta.commons.interceptor.LoginInterceptor;

@Configuration
@ComponentScan(basePackageClasses = AppConfig.class)
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableCaching(proxyTargetClass = true)
@PropertySource({"classpath:jdbc.properties", "classpath:site.properties", "classpath:mail.properties"})
public class AppConfig extends DefaultAppConfig {

    @Bean
    public LoginInterceptor loginInterceptor() {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        return new LoginInterceptor();
    }
    
    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/*");
        registry.addInterceptor(adminInterceptor()).addPathPatterns("/admin/**");
        super.addInterceptors(registry);
    }

}
