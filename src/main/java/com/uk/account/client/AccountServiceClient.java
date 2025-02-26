package com.uk.account.client;


import org.springframework.stereotype.Service;

@Service
public class AccountServiceClient {
    private final another.ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public AccountServiceClient(another.ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub) {
        this.productServiceBlockingStub = productServiceBlockingStub;
    }

    public String getUserInfo(String keyword) {
        another.Product.Query query = another.Product.Query.newBuilder()
                .setKeyword(keyword)
                .build();

        another.Product.Result result = productServiceBlockingStub.search(query);
        return result.getData();
    }

}
