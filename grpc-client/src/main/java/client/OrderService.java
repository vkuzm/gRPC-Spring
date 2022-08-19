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
        .setFirstName(order.getFirstname())
        .setLastName(order.getLastname())
        .setOrderDate(order.getOrderDate().toString())
        .setTotalPrice(order.getTotalPrice());

    for (int i = 0; i < order.getProducts().size(); i++) {
      request.setProducts(i, Product.newBuilder()
          .setId(order.getProducts().get(i).getId())
          .setName(order.getProducts().get(i).getName())
          .setPrice(order.getProducts().get(i).getPrice())
          .build());
    }

    return orderServiceStub.sendOrder(request.build()).getOrderId();
  }
}