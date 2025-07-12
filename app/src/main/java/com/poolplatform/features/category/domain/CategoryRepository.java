package com.poolplatform.features.category.domain;

import java.util.List;
import java.util.Optional;

import com.poolplatform.features.category.domain.models.Category;

public interface CategoryRepository {
    /**
     * Counts the total number of categories.
     *
     * @return the count of categories
     */
    int countCategories();

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories
     */
    List<Category> get();

    /**
     * Retrieves a category by its id.
     *
     * @param id the id of the category
     * @return the category if found, null otherwise
     */
    Optional<Category> get(String id);

    /**
     * Saves a new or existing category.
     *
     * @param category the category to save
     */
    void save(Category category);
}
