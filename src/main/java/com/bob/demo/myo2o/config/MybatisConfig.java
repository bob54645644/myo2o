package com.bob.demo.myo2o.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午4:18:32 
* 类说明 
*/
@Configuration
@MapperScan("com.bob.demo.myo2o.dao")
public class MybatisConfig {
	@Autowired
	private DataSource dataSource;
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		factoryBean.setConfigLocation(resolver.getResource("classpath:mybatis.xml"));
		
		return factoryBean;
		
	}
}
