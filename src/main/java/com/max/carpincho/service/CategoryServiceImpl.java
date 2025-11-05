package com.max.carpincho.service;

import com.max.carpincho.persistence.entity.Category;
import com.max.carpincho.persistence.repository.CategoryRepository;
import com.max.carpincho.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Optional<Category> saveCategoryIfNotExists(String nameCategory) {
        return Optional.of(
                this.categoryRepository.findCategoryByNameCategory(nameCategory)
                        .map((category)->{
                            System.out.println("Ya existe la categoria: " + nameCategory);
                            return category;
                        }).orElse(this.categoryRepository.save(
                                Category.builder().nameCategory(nameCategory).build()
                        ))
        );
    }

    @Override
    public Category findByName(String name) {
        return this.categoryRepository.findCategoryByNameCategory(name).orElseThrow();
    }
}
