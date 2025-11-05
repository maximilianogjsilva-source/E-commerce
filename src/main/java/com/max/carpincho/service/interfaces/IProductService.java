package com.max.carpincho.service.interfaces;

import com.max.carpincho.persistence.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> listAllProduct();

    Optional<Product> getById(Integer idProduct);

    Product saveProduct(Product product);

    Product editProduct(Integer idProduct,Product newProduct);

    Optional<Product> deleteProductById(Integer idProduct);

    List<Product> saveAll(List<Product> products);

}
