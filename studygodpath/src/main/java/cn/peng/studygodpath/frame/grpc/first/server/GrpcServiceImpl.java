package cn.peng.studygodpath.frame.grpc.first.server;

import cn.peng.studygodpath.frame.grpc.first.api.HelloReply;
import cn.peng.studygodpath.frame.grpc.first.api.HelloRequest;
import cn.peng.studygodpath.frame.grpc.first.api.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class GrpcServiceImpl extends SimpleGrpc.SimpleImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply data = HelloReply.newBuilder().setMessage("Hello ===>" + request.getName()).build();
        responseObserver.onNext(data);
        responseObserver.onCompleted();

    }
}
