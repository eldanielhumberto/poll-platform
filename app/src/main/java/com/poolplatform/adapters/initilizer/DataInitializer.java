package com.poolplatform.adapters.initilizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.poolplatform.features.category.domain.CategoryRepository;
import com.poolplatform.features.category.domain.models.Category;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.countCategories() == 0) {
            // Initialize categories or any other data if needed
            System.out.println("No categories found, initializing default categories...");

            categoryRepository.save(new Category(null, "Tecnologia", "bg-blue-100 text-blue-800", null));
            categoryRepository.save(new Category(null, "Entretenimiento", "bg-purple-100 text-purple-800", null));
            categoryRepository.save(new Category(null, "Salud", "bg-green-100 text-green-800", null));
            categoryRepository.save(new Category(null, "Trabajo", "bg-orange-100 text-orange-800", null));
            categoryRepository.save(new Category(null, "Social", "bg-pink-100 text-pink-800", null));
            categoryRepository.save(new Category(null, "Medio Ambiente", "bg-emerald-100 text-emerald-800", null));

            System.out.println("Default categories initialized.");
        }

    }
}
