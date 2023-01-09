package com.erdemnayin.redis.service;

import com.erdemnayin.redis.model.Product;
import com.erdemnayin.redis.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(cacheNames = "getAllProductsCache")
    public List<Product> getAllProducts(){

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Iterable<Product> iterableProducts = productRepository.findAll();
        return Streamable.of(iterableProducts).toList();
    }

    @CacheEvict(cacheNames = "getAllProductsCache")
    public void clearCache(){
        logger.info("Cache: getAllProductsCache cleared for method: List<Product> getAllProducts()");
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find product by id: " + id));
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product){
        Product productFromDb = getProductById(id);

        productFromDb.setName(product.getName());
        productFromDb.setPrice(product.getPrice());

        return productRepository.save(productFromDb);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
