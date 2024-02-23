package ua.spro.mongodemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.spro.mongodemo.product.Product;
import ua.spro.mongodemo.product.ProductRepository;

@SpringBootApplication
public class MongoDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(MongoDemoApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(
      ProductRepository productRepository
  ){
    return args -> {
    var product = Product.builder()
        .name("iPhone")
        .description("Smart phone")
        .build();
    productRepository.insert(product);
    };
  }
}
