package cn.peng.studygodpath.frame.grpc.first.client;

import cn.peng.studygodpath.frame.grpc.first.api.HelloRequest;
import cn.peng.studygodpath.frame.grpc.first.api.SimpleGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @GrpcClient("local-grpc-server")
    private SimpleGrpc.SimpleBlockingStub blockServiceStub;

    public String receiveGreeting(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        return blockServiceStub.sayHello(request).getMessage();
    }

}
