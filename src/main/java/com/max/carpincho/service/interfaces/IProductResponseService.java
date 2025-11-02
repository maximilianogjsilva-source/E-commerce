package com.max.carpincho.service.interfaces;

import com.max.carpincho.controller.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductResponseService {

    List<ProductDTO> listAll();

    ProductDTO saveProduct(ProductDTO productDTO);

    ProductDTO getById(Integer id);

    ProductDTO editProduct(Integer idProduct, ProductDTO newProductDTO);

    ResponseEntity<?> deleteProductById(Integer idProduct);

    List<ProductDTO> setUp();

}
