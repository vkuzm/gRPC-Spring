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
    var order = new Order();
    order.setFirstname("Alex");
    order.setLastname("Foo");
    order.setOrderDate(new Date());
    order.setTotalPrice(200);

    var product1 = new Product();
    product1.setId(1);
    product1.setName("Product name 1");
    product1.setPrice(150);

    var product2 = new Product();
    product1.setId(2);
    product1.setName("Product name 2");
    product1.setPrice(50);

    order.setProducts(List.of(product1, product2));
    return order;
  }
}