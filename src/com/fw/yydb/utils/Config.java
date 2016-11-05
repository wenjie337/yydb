package com.fw.yydb.utils;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config.properties")
public class Config {
	
	@Autowired
	private Environment env;
	
	public String getValue(String key){
		return env.getProperty(key);
	}
	
	public  int getActivityPageSize(){
		String str = getValue("activity.page.size");
		if(StringUtils.isBlank(str)){
			return 0;
		}
		return Integer.parseInt(str);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	public int getLotteryInterval(){
		String str = getValue("lotteryInterval");
		if(StringUtils.isBlank(str)){
			return 5000;
		}
		return Integer.parseInt(str);
	}
	
	public int getWyTimeOut(){
		String str = getValue("wangyiTimeout");
		if(StringUtils.isBlank(str)){
			return 5000;
		}
		return Integer.parseInt(str);
	}
	
	
}
