package com.demo.restful.config;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class SpringBeans {
	
	//@Value("${define property in application}")
	private int maxConnectionCount;
	
	//@Value("${define property in application}")
	private int maxPerRoute;

	//@Autowired
	//SSLContextConfigurator sslContextConfigurator;
	
	/*
	@Bean 
	public CloseableHttpClient messageSender() {
		final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContextConfigurator.getSslContext(),
		        new NoopHostnameVerifier());
		
		final Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
		        .<ConnectionSocketFactory>create().register("https", sslsf)
		        .register("http", PlainConnectionSocketFactory.getSocketFactory()).build();
		
		final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
		        socketFactoryRegistry);
		connectionManager.setMaxTotal(maxConnectionCount);
		connectionManager.setDefaultMaxPerRoute(maxPerRoute);
		
		final CloseableHttpClient httpClient = HttpClients.custom()
		        //.addInterceptorFirst(new ContentLengthHeaderRemover())
		        //.addInterceptorFirst(new HttpVersionSetterInterceptor())
		        .setConnectionManager(connectionManager)
		        .setSSLSocketFactory(sslsf)
		        .build();
		return httpClient;
	}*/
	
	
	@Bean
	public DataSource dataSource() {

		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
			.addScript("db/sql/create-db.sql")
			.addScript("db/sql/insert-data.sql")
			.build();
		return db;
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
