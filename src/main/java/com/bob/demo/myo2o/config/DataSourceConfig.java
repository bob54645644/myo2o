package com.bob.demo.myo2o.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bob.demo.myo2o.utils.DESUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/** 
* @author bob 
* @version 创建时间：2018年8月18日 下午8:48:57 
* 类说明 
*/
@Configuration
public class DataSourceConfig {
	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver}")
	private String driver;
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(DESUtil.getDecryptString(username));
		dataSource.setPassword(DESUtil.getDecryptString(password));
		dataSource.setDriverClass(driver);
		
		dataSource.setMaxPoolSize(30);
		dataSource.setMinPoolSize(10);
		dataSource.setAutoCommitOnClose(false);
		dataSource.setCheckoutTimeout(10000);
		dataSource.setAcquireRetryAttempts(2);
		
		return dataSource;
	}

}
