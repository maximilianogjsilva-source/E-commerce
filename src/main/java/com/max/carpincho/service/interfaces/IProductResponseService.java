package com.max.carpincho.service.interfaces;

import com.max.carpincho.controller.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IProductResponseService {

    List<ProductDTO> listAll();

    ProductDTO saveProduct(ProductDTO productDTO);

    Optional<ProductDTO> getById(Integer id);

    ProductDTO editProduct(Integer idProduct, ProductDTO newProductDTO);

    Optional<ProductDTO> deleteProductById(Integer idProduct);

    List<ProductDTO> setUp();

}
