package com.demo.ecom.controller;

import com.demo.ecom.dto.ApiResponse;
import com.demo.ecom.model.Category;
import com.demo.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService service;

    @PostMapping("/categories")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody Category category) {
         service.saveProduct(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category has been added"), HttpStatus.CREATED);

    }

    @GetMapping("/categories")
    public List<Category> listAllCategories(){
        return service.listAllCategories();
    }


}
