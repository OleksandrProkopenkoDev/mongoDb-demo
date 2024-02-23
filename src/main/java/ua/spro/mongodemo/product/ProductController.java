package ua.spro.mongodemo.product;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<String> save(@RequestBody Product product){
    return ResponseEntity.ok(productService.save(product));
  }

  @GetMapping
  public ResponseEntity<List<Product>> findAll(){
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/{product-id}")
  public ResponseEntity<Product> findById(
      @PathVariable("product-id") String productId
  ){
    return ResponseEntity.ok(productService.findById(productId));
  }

  @DeleteMapping("/{product-id}")
  public ResponseEntity<Void> delete(
      @PathVariable("product-id") String productId
  ){
    productService.delete(productId);
    return ResponseEntity.accepted().build();
  }
}

