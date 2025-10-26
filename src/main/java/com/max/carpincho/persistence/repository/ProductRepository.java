package com.max.carpincho.persistence.repository;

import com.max.carpincho.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {

}
