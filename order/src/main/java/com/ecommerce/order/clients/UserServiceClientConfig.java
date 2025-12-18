package com.ecommerce.order.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration

public class UserServiceClientConfig {


    @Bean
    public UserServiceClient restUserClientInterface(RestClient.Builder userClientBuilder) {
        RestClient restClient = userClientBuilder
                .baseUrl("http://user-service")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError,
                        ((request, response) -> Optional.empty()))
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        UserServiceClient serviceClient = factory.createClient(UserServiceClient.class);
        return serviceClient;
    }

}
