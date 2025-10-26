package com.max.carpincho.persistence.repository;

import com.max.carpincho.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Boolean existsCategoryByNameCategory(String nameCategory);

    Category findCategoryByNameCategory(String nameCategory);

}
