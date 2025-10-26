package com.max.carpincho.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductControllerTestRestTemplatesTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void testSave_Status_201(){
//        //Given
//        Product product1 = DataProvider.getProduct();
//
//        //When
//        ResponseEntity<Product> productResponse = testRestTemplate.postForEntity(
//                "http://localhost:8080/demo-products/add-item", product1, Product.class);
//        Product productSaved = productResponse.getBody();
//
//        //Then
//        assertEquals(HttpStatus.CREATED, productResponse.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON, productResponse.getHeaders().getContentType());
//
//        assertNotNull(productSaved);
//        assertEquals(product1.getTitle(), productSaved.getTitle());
    }

    @Test
    @Order(2)
    void testLisAllProducts_Status_200(){
//        //When
//        ResponseEntity<Product[]> productsResponse = testRestTemplate.getForEntity(
//                "http://localhost:8080/demo-products/list-items", Product[].class);
//        List<Product> productList = Arrays.asList(productsResponse.getBody());
//
//        //Then
//        assertEquals(HttpStatus.OK, productsResponse.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON, productsResponse.getHeaders().getContentType());
//        assertEquals(2, productList.get(0).getIdProduct());
//        assertEquals("tshirta.jpg", productList.get(0).getSrcImg());

    }

    @Test
    @Order(3)
    void testGetProductById_Status_200(){
//        //When
//        ResponseEntity<Product> response = testRestTemplate.getForEntity(
//                "http://localhost:8080/demo-products/get-id/2", Product.class);
//        Product product2 = response.getBody();
//
//        //Then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
//        assertNotNull(product2);
//        assertEquals(2, product2.getIdProduct());
//        assertEquals("tshirta.jpg", product2.getSrcImg());


    }

    @Test
    @Order(4)
    void testDeleteProduct_Status_NotContent(){

    }

}




