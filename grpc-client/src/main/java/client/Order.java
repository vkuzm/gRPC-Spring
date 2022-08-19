package client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

  private String firstname;
  private String lastname;
  private int totalPrice;
  private Date orderDate;
  private List<Product> products;
}
