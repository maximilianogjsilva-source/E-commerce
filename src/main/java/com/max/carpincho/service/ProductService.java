package com.max.carpincho.service;

import com.max.carpincho.exception.ProductNotFoundException;
import com.max.carpincho.persistence.entity.Product;
import com.max.carpincho.persistence.repository.ProductRepository;
import com.max.carpincho.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> listAllProduct() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Product> getById(Integer idProduct) {
        return Optional.of(this.repository.findById(idProduct).orElseThrow(
                () -> new ProductNotFoundException(idProduct)
        ));
    }

    @Override
    public Product saveProduct(Product product) {
        return this.repository.save(product);
    }

    @Override
    public Product editProduct(Integer idProduct, Product newProduct) {
        return this.repository.findById(idProduct)
                .map(product1 -> {
                    product1 = Product.builder().idProduct(idProduct)
                            .title(newProduct.getTitle())
                            .srcImg(newProduct.getSrcImg())
                            .description(newProduct.getDescription())
                            .price(newProduct.getPrice()).build();
                    return this.repository.save(product1);
                }).orElseGet(()->{
                    return this.repository.save(newProduct);
                });
    }

    @Override
    public Optional<Product> deleteProductById(Integer idProduct) {
        return Optional.of(
                this.repository.findById(idProduct).map((product)->{
                    repository.delete(product);
                    return product;
                }).orElseThrow(()-> new ProductNotFoundException(idProduct))
        );
    }

    @Override
    public List<Product> saveAll(List<Product> products){
        return this.repository.saveAll(products);
    }
}
