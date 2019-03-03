package com.xiaozhu.repocket.configuration;


import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfiguration {

    @Bean(value = "http",initMethod = "start",destroyMethod = "destroy")
    HttpClient httpClient() throws Exception{
        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.start();
        return httpClient;
    }
}
