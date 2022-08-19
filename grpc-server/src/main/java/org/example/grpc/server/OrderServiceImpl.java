package org.example.grpc.server;

import io.grpc.stub.StreamObserver;
import java.util.UUID;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.contract.OrderRequest;
import org.example.grpc.contract.OrderResponse;
import org.example.grpc.contract.OrderServiceGrpc;

@GrpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

  @Override
  public void sendOrder(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
    var createdOrderId = saveOrderToDb(request);

    OrderResponse response = OrderResponse.newBuilder()
        .setOrderId(createdOrderId)
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private String saveOrderToDb(OrderRequest request) {
    System.out.println(request.toString());

    return UUID.randomUUID().toString();
  }
}