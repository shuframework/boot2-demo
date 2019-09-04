package com.shuframework.boot2.esclient.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@Data
public class EsRestClientConfig {

    private static final String SCHEMA = "http";
    /** 连接超时时间,单位毫秒 */
    private static final int CONNECT_TIME_OUT = 1000;
    /** 读取超时时间,单位毫秒 */
    private static final int SOCKET_TIME_OUT = 30000;
    /** 连接超时时间,单位毫秒 */
    private static final int CONNECTION_REQUEST_TIME_OUT = 500;

    private static final int MAX_CONNECT_NUM = 1000;
    private static final int MAX_CONNECT_PER_ROUTE = 1000;

//    private static boolean uniqueConnectTimeConfig = true;
//    private static boolean uniqueConnectNumConfig = true;
    private RestClientBuilder builder;
    private RestClient restClient;
    private RestHighLevelClient restHighLevelClient;


    private String clusterName;
    private String host;
    private int port;
    private String user;
    private String password;


    public void init() {
        builder = RestClient.builder(new HttpHost(host, port, SCHEMA));
        setConnectTimeOutConfig();
        setMutiConnectConfig();
        restClient = builder.build();
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    // 主要关于异步httpclient的连接延时配置  
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(builder -> {
            builder.setConnectTimeout(CONNECT_TIME_OUT);
            builder.setSocketTimeout(SOCKET_TIME_OUT);
            builder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT);
            return builder;
        });
    }

    /**
     * 主要关于异步httpclient的连接数配置
     */
    public void setMutiConnectConfig() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));

        builder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
            httpAsyncClientBuilder.setMaxConnTotal(MAX_CONNECT_NUM);
            httpAsyncClientBuilder.setMaxConnPerRoute(MAX_CONNECT_PER_ROUTE);
            httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return httpAsyncClientBuilder;
        });
    }

    @Bean
    public RestHighLevelClient getHighLevelClient() {
        if (restHighLevelClient == null) {
            init();
        }
        return restHighLevelClient;
    }

//    public RestClient getClient() {
//        return restClient;
//    }

    public void close() {
        if (restHighLevelClient != null) {
            try {
                restHighLevelClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
