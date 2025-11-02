package com.max.carpincho;

import com.max.carpincho.persistence.entity.Category;
import com.max.carpincho.persistence.entity.Product;

import java.util.List;

public class DataProvider {

    public static List<Product> listAll(){
        System.out.println("--> Devolviendo List<Product>/Mock");
        Category cat = new Category(1, "clothes");
        return List.of(
                new Product(1, "Jean", "jean.jpg", "Classic Jean", 20500, List.of(cat)),
                new Product(2, "Coat", "coat.jpg", "Cotton Coat", 8300, List.of(cat)),
                new Product(3, "Skirt", "skirt.jpg", "Sublimated Skirt", 13000, List.of(cat))
        );
    }

    public static Product getProduct(){
        System.out.println("--> Devolviendo Optional<Product>/Mock ");
        return Product.builder()
                .title("Jean")
                .srcImg("jean.jpg")
                .description("Classic Jean")
                .categories(List.of(new Category(1, "clothes")))
                .price(20500).build();
    }

    public static Product getProductId(){
        System.out.println("--> Devolviendo Optional<Product>/Mock ");
        return Product.builder()
                .idProduct(1)
                .title("Jean")
                .srcImg("jean.jpg")
                .description("Classic Jean")
                .categories(List.of(new Category(1, "clothes")))
                .price(20500).build();
    }

    public static Product getNewProduct(){
        System.out.println("--> Devolviendo Optional<Product>/Mock ");
        return Product.builder()
                .idProduct(null)
                .title("Shirt")
                .srcImg("shirt.jpg")
                .description("Blue Shirt")
                .price(7600)
                .categories(List.of(new Category(1, "clothes")))
                .build();
    }

    public static Product getNewProductId(){
        System.out.println("--> Devolviendo Optional<Product>/Mock ");
        return Product.builder()
                .idProduct(4)
                .title("Shirt")
                .srcImg("shirt.jpg")
                .description("Blue Shirt")
                .price(7600)
                .categories(List.of(new Category(1, "clothes")))
                .build();
    }

    public static Product getProductEdited(){
        System.out.println("--> Devolviendo Optional<Product>/Mock ");
        return Product.builder()
                .title("Jean Azul")
                .srcImg("jeanaa.jpg")
                .description("Classic Jean Azul")
                .categories(List.of(new Category(1, "clothes")))
                .price(21500).build();
    }

    public static Product getProductEditedId(){
        System.out.println("--> Devolviendo Optional<Product>/Mock ");
        return Product.builder()
                .idProduct(1)
                .title("Jean Azul")
                .srcImg("jeanaa.jpg")
                .description("Classic Jean Azul")
                .categories(List.of(new Category(1, "clothes")))
                .price(21500).build();
    }

}
