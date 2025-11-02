package com.max.carpincho.service.interfaces;

import com.max.carpincho.persistence.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> listAllProduct();

    Product getById(Integer idProduct);

    Product saveProduct(Product product);

    Product editProduct(Integer idProduct,Product newProduct);

    Boolean deleteProductById(Integer idProduct);

    List<Product> saveAll(List<Product> products);

}
