package com.max.carpincho.service.interfaces;

import com.max.carpincho.persistence.entity.Category;

import java.util.Optional;

public interface ICategoryService {

    Optional<Category> saveCategoryIfNotExists(String nameCategory);

    Category findByName(String name);

}
