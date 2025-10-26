package com.max.carpincho.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString(exclude = {"categories"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    Integer idProduct;

    String title;

    @Column(name = "src_img")
    String srcImg;

    String description;

    Integer price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category"))
    List<Category> categories;

}
