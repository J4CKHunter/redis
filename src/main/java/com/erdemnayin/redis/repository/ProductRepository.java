package com.erdemnayin.redis.repository;

import com.erdemnayin.redis.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
