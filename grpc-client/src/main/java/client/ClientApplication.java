package client;

import java.util.Date;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

  public static void main(String[] args) {
    var app = SpringApplication.run(ClientApplication.class, args);
    var foobarService = app.getBean(OrderService.class);

    Order order = generateOrder();
    String response = foobarService.sendOrder(order);

    System.out.println("Order created with order id: " + response);
  }

  private static Order generateOrder() {
    var order = Order.builder()
        .firstname("Alex")
        .lastname("Foo")
        .orderDate(new Date())
        .totalPrice(200);

    var product1 = Product.builder()
        .id(1)
        .name("Product name 1")
        .price(150)
        .build();

    var product2 = Product.builder()
        .id(2)
        .name("Product name 2")
        .price(50)
        .build();

    order.products(List.of(product1, product2));

    return order.build();
  }
}