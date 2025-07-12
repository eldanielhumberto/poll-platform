package com.poolplatform.features.category.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.features.category.adapters.mappers.CategoryMapper;
import com.poolplatform.features.category.domain.CategoryService;
import com.poolplatform.features.category.domain.models.CategoryResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> getCategories() {
        ResponseDTO<List<CategoryResponse>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Get all categories");
        responseDTO.setData(categoryService.getCategories().stream().map(CategoryMapper::toCategoryResponse).toList());

        return ResponseEntity.ok(responseDTO);
    }

}
