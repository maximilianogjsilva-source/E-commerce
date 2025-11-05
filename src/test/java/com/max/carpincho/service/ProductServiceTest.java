package com.max.carpincho.service;

import com.max.carpincho.DataProvider;
import com.max.carpincho.exception.ProductNotFoundException;
import com.max.carpincho.persistence.entity.Product;
import static org.junit.jupiter.api.Assertions.*;

import com.max.carpincho.persistence.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    @Test
    void testListAllProduct(){
        //When
        when(this.repository.findAll()).thenReturn(DataProvider.listAll());
        List<Product> productList = this.service.listAllProduct();

        //Then
        assertNotNull(productList);
        assertFalse(productList.isEmpty());
        assertEquals("Jean", productList.get(0).getTitle());
        assertEquals("jean.jpg", productList.get(0).getSrcImg());
        assertEquals("Classic Jean", productList.get(0).getDescription());
        assertEquals(20500, productList.get(0).getPrice());
        verify(this.repository).findAll();

    }

    @Test
    void testGetById(){
        //Given
        Integer id = 1;

        //When
        when(this.repository.findById( anyInt() ))
                .thenReturn(Optional.of(DataProvider.getProductId()));
        Product product = this.service.getById(id).get();

        //Then
        assertNotNull(product);
        assertEquals(1, product.getIdProduct());
        assertEquals("Jean", product.getTitle());
        assertEquals("jean.jpg", product.getSrcImg());
        assertEquals("Classic Jean", product.getDescription());
        verify(this.repository).findById( anyInt() );

    }

    @Test
    void testGetByIdThrow(){
        //Given
        int id = 5;

        //Then
        assertThrows(ProductNotFoundException.class, ()->{
            this.service.getById(id);
        });

    }

    @Test
    void testSaveProduct(){
        //Given
        Product product = DataProvider.getNewProduct();

        //When
        when(this.repository.save( product )).thenReturn( product );
        this.service.saveProduct(product);

        //Then
        verify(this.repository).save(any(Product.class));

    }

    @Test
    void testEditProduct(){
        int id = 1;

        //When
        when(this.repository.findById( anyInt() ))
                .thenReturn(Optional.of(DataProvider.getProductId()));
        when(this.repository.save( any() )).thenReturn(DataProvider.getProductEdited());
        Product product = this.service.editProduct(id, DataProvider.getProduct());

        //Then
        assertEquals("Jean Azul", product.getTitle());
        verify(this.repository).findById( anyInt() );
        verify(this.repository).save( any(Product.class) );

    }

    @Test
    void testEditProductThrow(){
        //Given
        int id = 5;
        Product newProduct = DataProvider.getNewProduct();

        //When
        when(this.repository.save( any() )).thenReturn(newProduct);
        Product edit = this.service.editProduct(id, newProduct);

        //Then
        verify(this.repository).save( any(Product.class) );
        assertEquals("Shirt", edit.getTitle());

    }

    @Test
    void testDeleteProductById(){
        //Given
        int id = 1;

        //When
        when(this.repository.findById(anyInt()))
                .thenReturn(Optional.of(DataProvider.getProduct()));
        this.service.deleteProductById(id);

        //Then
        verify(this.repository).findById( anyInt() );
        verify(this.repository).deleteById( anyInt() );

    }

    @Test
    void testDeleteProductByIdFalse(){
        //Given
        int id = 5;

        //When
        when(this.repository.findById( anyInt() ))
                .thenReturn(Optional.ofNullable(null));
        this.service.deleteProductById(id);

        //Then
        verify(this.repository).findById( anyInt() );

    }

}




