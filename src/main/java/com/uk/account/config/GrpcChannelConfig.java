package com.uk.account.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcChannelConfig {
    @Bean("ProductGrpcChannel")
    public ManagedChannel productGrpcChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 5002)
                .usePlaintext()
                .build();
    }
}
