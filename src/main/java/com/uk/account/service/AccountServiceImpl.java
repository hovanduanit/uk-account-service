package com.uk.account.service;

import account.Account;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AccountServiceImpl extends account.AccountServiceGrpc.AccountServiceImplBase {
    @Override
    public void getAccount(Account.GetAccountRequest request, StreamObserver<Account.GetAccountResponse> responseObserver) {
                Account.GetAccountResponse response = Account.GetAccountResponse.newBuilder()
                        .setName("John Doe")
                        .setEmail("Duan@example.com")
                .build();

        // Trả kết quả về client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
