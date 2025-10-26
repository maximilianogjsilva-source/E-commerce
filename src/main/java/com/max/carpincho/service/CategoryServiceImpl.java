package com.max.carpincho.service;

import com.max.carpincho.persistence.entity.Category;
import com.max.carpincho.persistence.repository.CategoryRepository;
import com.max.carpincho.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategoryIfNotExists(String nameCategory) {
        if(!this.categoryRepository.existsCategoryByNameCategory(nameCategory)){
            System.out.println("No existe la categoria: " + nameCategory);
            this.categoryRepository.save(Category.builder().nameCategory(nameCategory).build());
        }
    }

    @Override
    public Category findByName(String name) {
        return this.categoryRepository.findCategoryByNameCategory(name);
    }
}
