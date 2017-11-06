package com.demo.restful.config;

import java.security.KeyStore;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

//@Component
public class SSLContextConfigurator {
	
	 @Value("classpath:${keystore.file}")  Resource keystoreFile;
     @Value("${keystore.type}")  String keystoreType;
     @Value("${keystore.pass}")  String keystorePass;
     
     @Value("classpath:${truststore.file}")  Resource truststoreFile;
     @Value("${truststore.type}")  String truststoreType;
     @Value("${truststore.pass}")  String truststorePass;

	private static final Logger LOGGER = LoggerFactory.getLogger(SSLContextConfigurator.class);

	private SSLContext sslContext;
	
	public SSLContextConfigurator() {
		
	}

	@PostConstruct
	public void init() throws Exception {
		
		try {
			final KeyStore keyStore = KeyStore.getInstance(keystoreType);
			keyStore.load(keystoreFile.getInputStream(), keystorePass.toCharArray());
			
			final KeyStore trustStore = KeyStore.getInstance(truststoreType);
			trustStore.load(truststoreFile.getInputStream(), truststorePass.toCharArray());
			
			final TrustManagerFactory trustManager = TrustManagerFactory
			        .getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustManager.init(trustStore);

			final TrustStrategy ts = (chain, authType) -> true;

			//
			sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, keystorePass.toCharArray())
			        .loadTrustMaterial(trustStore, ts).build();

		} catch (final Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		
	}
	
	public SSLContext getSslContext() {
		return sslContext;
	}

}
