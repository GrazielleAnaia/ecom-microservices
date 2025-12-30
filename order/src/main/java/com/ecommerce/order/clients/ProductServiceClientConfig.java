package com.ecommerce.order.clients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration
public class ProductServiceClientConfig {


    @Bean
    public ProductServiceClient restProductClientInterface(@LoadBalanced RestClient.Builder clientBuilder) {
        RestClient restClient = clientBuilder
                .baseUrl("http://product-service")

                .defaultStatusHandler(HttpStatusCode::is4xxClientError,
                        ((request, response) -> Optional.empty()))
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        ProductServiceClient serviceClient = factory.createClient(ProductServiceClient.class);
        return serviceClient;
    }

//    @Bean
//    public ProductServiceClient restProductClientInterface(WebClient.Builder clientBuilder) {
//        WebClient client = clientBuilder.baseUrl("http://product-service")
//
////                .defaultStatusHandler(HttpStatusCode::is4xxClientError,
////                        ((request, response) -> Optional.empty()))
//                .build();
//        WebClientAdapter adapter = WebClientAdapter.create(client);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//        ProductServiceClient serviceClient = factory.createClient(ProductServiceClient.class);
//        return serviceClient;
//    }
}
