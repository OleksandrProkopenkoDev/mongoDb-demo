package ua.spro.mongodemo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@AllArgsConstructor
@Builder
public class Product {
  @Id
  private String id;
  private String name;
  private String description;
}

