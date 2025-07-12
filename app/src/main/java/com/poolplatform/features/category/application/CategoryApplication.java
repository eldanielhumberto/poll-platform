package com.poolplatform.features.category.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.category.domain.CategoryRepository;
import com.poolplatform.features.category.domain.CategoryService;
import com.poolplatform.features.category.domain.models.Category;

@Service
public class CategoryApplication implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public int countCategories() {
        return categoryRepository.countCategories();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.get();
    }

    @Override
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.get(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

}
