package com.max.carpincho.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"id", "title", "srcImg", "description", "price", "categories"})
public record ProductDTO(Integer id,
                         String title,
                         String srcImg,
                         String description,
                         Integer price,
                         List<String> categories) {

}
