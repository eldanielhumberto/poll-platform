package com.poolplatform.features.category.adapters.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.category.adapters.entities.CategoryEntity;
import com.poolplatform.features.category.adapters.mappers.CategoryMapper;
import com.poolplatform.features.category.domain.CategoryRepository;
import com.poolplatform.features.category.domain.models.Category;

@Repository
public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, String>, CategoryRepository {

    @Override
    public default int countCategories() {
        return (int) count();
    }

    @Override
    default List<Category> get() {
        return findAll().stream()
                .map(CategoryMapper::toCategory)
                .toList();
    }

    @Override
    public default Optional<Category> get(String id) {
        return findById(id).map(CategoryMapper::toCategory);
    }

    @Override
    public default void save(Category category) {
        save(CategoryMapper.toCategoryEntity(category));
    }
}
