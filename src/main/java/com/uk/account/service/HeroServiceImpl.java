package com.uk.account.service;

import hero.HeroOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HeroServiceImpl extends hero.HeroesServiceGrpc.HeroesServiceImplBase {
    @Override
    public void findOne(HeroOuterClass.HeroById request, StreamObserver<HeroOuterClass.Hero> responseObserver) {
        HeroOuterClass.Hero response = HeroOuterClass.Hero.newBuilder()
                .setName("Halo")
                .setId(request.getId())
                .build();

        // Trả kết quả về client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<HeroOuterClass.HeroById> findMany(StreamObserver<HeroOuterClass.Hero> responseObserver) {
        return super.findMany(responseObserver);
    }
}
