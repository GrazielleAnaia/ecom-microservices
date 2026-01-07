package com.ecommerce.order.clients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestClientConfig {

//    @LoadBalanced
//    @Bean
//    public RestClient.Builder restClientBuilderLb() {
//        return RestClient.builder();
//    }


    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuild() {
        return WebClient.builder();
    }

    //Modification needed to avoid circular dependency
//    @Primary
//    @Bean
//    RestClient.Builder restClientBuilder() {
//        return RestClient.builder();
//    }

}
