package ua.spro.mongodemo.product;

import static java.lang.String.format;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public String save(Product product) {
    return productRepository.save(product).getId();
  }

  public Product findById(String id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException(format("product with id [%s] is not found", id)));
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public void delete(String id) {
    productRepository.deleteById(id);
  }
}
