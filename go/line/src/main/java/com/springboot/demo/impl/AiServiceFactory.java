package com.springboot.demo.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.client.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;

@Component
public class AiServiceFactory {


    @Value("${openai.proxyHost}")
    private String proxyHost;
    /**
     * 代理端口
     */
    @Value("${openai.proxyPort}")
    private Integer proxyPort;
    /**
     * openai apikey
     */
    @Value("${openai.keys}")
    private String token;

    @Value("${openai.proxy}")
    private String proxyIp;

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10L);

    public OpenAiService createService() {
        ObjectMapper mapper = OpenAiService.defaultObjectMapper();
        // 创建 OkHttpClient，不设置代理
        OkHttpClient client = OpenAiService.defaultClient(token, DEFAULT_TIMEOUT).newBuilder().build();
        // 使用 OkHttpClient 实例创建 Retrofit
        Retrofit retrofit = OpenAiService.defaultRetrofit(client, mapper).newBuilder()
                // 确保这里的 baseUrl 是 OpenAI API 的基础 URL
                .baseUrl("https://api.openai.com/")
                .build();
        return new OpenAiService(retrofit.create(OpenAiApi.class), client.dispatcher().executorService());
    }
//    public OpenAiService createService() {
//
//        ObjectMapper mapper = OpenAiService.defaultObjectMapper();
//        // 设置代理
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
//        OkHttpClient client = OpenAiService.defaultClient(token, DEFAULT_TIMEOUT).newBuilder()
//                .proxy(proxy)
//                .build();
//        Retrofit retrofit = OpenAiService.defaultRetrofit(client, mapper).newBuilder().baseUrl(proxyIp).build();
//        return new OpenAiService(retrofit.create(OpenAiApi.class), client.dispatcher().executorService());
//
//    }
}
