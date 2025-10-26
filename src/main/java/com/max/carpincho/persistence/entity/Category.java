package com.max.carpincho.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Integer idCategory;

    @Column(name = "name_category")
    private String nameCategory;

}
