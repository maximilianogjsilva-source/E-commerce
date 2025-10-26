package com.max.carpincho.service.util;

import com.max.carpincho.controller.dto.ProductDTO;
import com.max.carpincho.persistence.entity.Category;
import com.max.carpincho.persistence.entity.Product;

import java.util.List;


public class ProductMapper {

    public static ProductDTO toProductDTO(Product product){

        //Se crea un ProductDTO basandose en el Product que se recibe
        return new ProductDTO(product.getIdProduct(),
                product.getTitle(),
                product.getSrcImg(),
                product.getDescription(),
                product.getPrice(),
                product.getCategories().stream().map(Category::getNameCategory).toList());
    }

    public static Product productWithCategories(ProductDTO productDTO, List<Category> categories){

        //Se crea y devuelve un Product basandose en el ProductDTO y list<Category> que se recibe
        return Product.builder()
                .title(productDTO.title())
                .srcImg(productDTO.srcImg())
                .description(productDTO.description())
                .price(productDTO.price())
                .categories(categories)
                .build();
    }

    public static Product toProduct(ProductDTO productDTO){
        return Product.builder()
                .idProduct(productDTO.id())
                .title(productDTO.title())
                .srcImg(productDTO.srcImg())
                .description(productDTO.description())
                .price(productDTO.price())
                .build();
    }

}
