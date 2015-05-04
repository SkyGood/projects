package com.uccyou.ta;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.uccyou.core.config.DefaultAppConfig;

@Configuration
@ComponentScan(basePackageClasses = AppConfig.class)
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableWebMvc
@PropertySource({"classpath:jdbc.properties", "classpath:site.properties", "classpath:mail.properties"})
public class AppConfig extends DefaultAppConfig {

}
