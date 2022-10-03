package com.demo.ecom.service;

import com.demo.ecom.model.Category;
import com.demo.ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category saveProduct(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> listAllCategories(){
        return categoryRepository.findAll();

    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

}
