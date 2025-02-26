package com.uk.account.config;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductGrpcStubConfig {
    @Bean
    public another.ProductServiceGrpc.ProductServiceBlockingStub accountServiceBlockingStub(@Qualifier("ProductGrpcChannel") ManagedChannel channel) {
        return another.ProductServiceGrpc.newBlockingStub(channel);
    }
}
