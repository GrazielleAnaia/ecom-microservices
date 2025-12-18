package com.ecommerce.order.clients;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration
@Slf4j
public class ProductServiceClientConfig {


    private static final Logger logger = LoggerFactory.getLogger(ProductServiceClientConfig.class);
    @Bean
    public ProductServiceClient restProductClientInterface(RestClient.Builder clientBuilder) {
        RestClient restClient = clientBuilder
                .baseUrl("http://product-service")

                .defaultStatusHandler(HttpStatusCode::is4xxClientError,
                        ((request, response) -> Optional.empty()))
                .build();
        logger.atDebug();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        ProductServiceClient serviceClient = factory.createClient(ProductServiceClient.class);
        return serviceClient;
    }
}
