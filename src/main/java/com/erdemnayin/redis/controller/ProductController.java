package com.erdemnayin.redis.controller;

import com.erdemnayin.redis.model.Product;
import com.erdemnayin.redis.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private Integer counter = 0;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        checkCounter();
        return ResponseEntity.ok(productService.getAllProducts());
    }

    private void checkCounter(){
        if(counter == 5){
            productService.clearCache();
            counter = 0;
        }else{
            counter++;
        }
        logger.info(String.format("Counter value: %s", counter));
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
