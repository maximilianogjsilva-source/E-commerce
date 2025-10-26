package com.max.carpincho.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.max.carpincho.DataProvider;
import com.max.carpincho.persistence.entity.Product;
import com.max.carpincho.persistence.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveProduct(){
        //Given
        Product product1 = DataProvider.getProduct();

        //When
        Product saveProduct = productRepository.save(product1);

        //Then
        assertThat(saveProduct).isNotNull();
        assertThat(saveProduct.getIdProduct()).isGreaterThan(0);

    }

    @Test
    void testFindAll(){
        //Given
        Product product1 = DataProvider.getProduct();
        Product product2 = Product.builder()
                .title("Shirt")
                .srcImg("shirt.jpg")
                .description("Blue Shirt")
                .price(7600).build();
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        List<Product> productList = productRepository.findAll();

        //Then
        assertThat(productList).isNotNull();
        assertThat(productList).isNotEmpty();
        assertThat(productList.size()).isEqualTo(2);

    }

    @Test
    void testFindById(){
        //Given
        Product product1 = DataProvider.getProduct();
        Product productSave = productRepository.save(product1);

        //When
        Product saveProduct = productRepository.findById(productSave.getIdProduct()).get();

        //Then
        assertThat(saveProduct).isNotNull();
        assertThat(saveProduct.getTitle()).isEqualTo(product1.getTitle());
        assertThat(saveProduct.getSrcImg()).isEqualTo(product1.getSrcImg());
        assertThat(saveProduct.getDescription()).isEqualTo(product1.getDescription());
        assertThat(saveProduct.getPrice()).isEqualTo(product1.getPrice());

    }

    @Test
    void updateProduct(){
        //Given
        Product product1 = productRepository.save(DataProvider.getProduct());

        //When
        Product saveProduct = productRepository.findById(product1.getIdProduct()).get();
        saveProduct = DataProvider.getProductEditedId();
        Product updatedProduct = productRepository.save(saveProduct);

        //Then
        assertThat(updatedProduct.getTitle()).isEqualTo(saveProduct.getTitle());
        assertThat(updatedProduct.getSrcImg()).isEqualTo(saveProduct.getSrcImg());
        assertThat(updatedProduct.getDescription()).isEqualTo(saveProduct.getDescription());
        assertThat(updatedProduct.getPrice()).isEqualTo(saveProduct.getPrice());

    }

    @Test
    void deletedProductById(){
        //Given
        productRepository.save(DataProvider.getProduct());

        //When
        productRepository.deleteById(1);
        Optional<Product> obtionalProduc = productRepository.findById(1);

        //Then
        assertThat(obtionalProduc).isEmpty();

    }

}




