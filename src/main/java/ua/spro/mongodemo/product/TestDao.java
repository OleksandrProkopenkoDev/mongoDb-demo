package ua.spro.mongodemo.product;

import java.util.List;

public class TestDao {

  public static void main(String[] args) {
    // Create a ProductDao instance
    ProductDao productDao = new ProductDao();

    // Create new Product objects with auto-generated IDs using the builder pattern
    Product product1 = Product.builder()
        .name("Product 1")
        .description("Description 1")
        .build();
    Product product2 = Product.builder()
        .name("Product 2")
        .description("Description 2")
        .build();

    // Test save method
    productDao.save(product1);
    productDao.save(product2);
    System.out.println("Products saved successfully.");

    // Test findAll method
    System.out.println("All products:");
    List<Product> products = productDao.findAll();
    product1.setId(products.get(0).getId());
    product2.setId(products.get(1).getId());
    for (Product product : products) {
      System.out.println(product);
    }

    // Test findById method
    System.out.println("Product with id " + product1.getId() + ": " + productDao.findById(product1.getId()));
    System.out.println("Product with id " + product2.getId() + ": " + productDao.findById(product2.getId()));

    // Test update method
    product1 = Product.builder()
        .id(product1.getId())
        .name("Updated Product 1")
        .description("Updated Description 1")
        .build();
    productDao.update(product1);
    System.out.println("Product with id " + product1.getId() + " after update: " + productDao.findById(product1.getId()));

    // Test delete method
    productDao.delete(product2);
    System.out.println("Product with id " + product2.getId() + " deleted.");

    // Test findAll method after delete
    System.out.println("All products after delete:");
    for (Product product : productDao.findAll()) {
      System.out.println(product);
    }
  }
}
