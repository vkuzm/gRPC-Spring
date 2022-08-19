package client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.grpc.contract.OrderRequest;
import org.example.grpc.contract.OrderRequest.Builder;
import org.example.grpc.contract.OrderServiceGrpc.OrderServiceBlockingStub;
import org.example.grpc.contract.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @GrpcClient("order")
  private OrderServiceBlockingStub orderServiceStub;

  public String sendOrder(Order order) {
    Builder request = OrderRequest.newBuilder()
        .setFirstname(order.getFirstname())
        .setLastname(order.getLastname())
        .setOrderDate(order.getOrderDate().toString())
        .setTotalPrice(order.getTotalPrice());


    order.getProducts().stream()
        .map(product -> Product.newBuilder()
            .setId(product.getId())
            .setName(product.getName())
            .setPrice(product.getPrice())
            .build())
        .forEach(request::addProducts);

    return orderServiceStub.sendOrder(request.build()).getOrderId();
  }
}