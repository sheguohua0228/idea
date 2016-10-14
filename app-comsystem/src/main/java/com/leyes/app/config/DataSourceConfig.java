package com.leyes.app.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfig implements EnvironmentAware {
	
	private final static Logger logger = LogManager.getLogger();

    private RelaxedPropertyResolver propertyResolver;  
    
    @Override
	public void setEnvironment(Environment env) {
    	this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");  
	}
    
    @Bean
    public DataSource dataSource() {  
    	logger.info("Configuring DataSource");  
          
        DruidDataSource datasource = new DruidDataSource();  
        datasource.setUrl(propertyResolver.getProperty("url"));  
        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));  
        datasource.setUsername(propertyResolver.getProperty("username"));  
        datasource.setPassword(propertyResolver.getProperty("password"));  
          
        return datasource;  
    }  

}