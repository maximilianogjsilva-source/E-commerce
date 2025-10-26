package com.max.carpincho.service.interfaces;

import com.max.carpincho.persistence.entity.Category;

public interface ICategoryService {

    void saveCategoryIfNotExists(String nameCategory);

    Category findByName(String name);

}
