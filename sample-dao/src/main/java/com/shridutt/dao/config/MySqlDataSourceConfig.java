package com.shridutt.dao.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(name = "sampleapp.datasource", havingValue = "mysql")
@Configuration
public class MySqlDataSourceConfig {

	@Value("${sampleapp.mysql.spring.datasource.username}")
	private String userName;

	@Value("${sampleapp.mysql.spring.datasource.password}")
	private String password;

	@Value("${sampleapp.mysql.spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${sampleapp.mysql.spring.datasource.url}")
	private String url;

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driverClassName);
		dataSourceBuilder.url(url);
		dataSourceBuilder.username(userName);
		dataSourceBuilder.password(password);
		return dataSourceBuilder.build();
	}
}
