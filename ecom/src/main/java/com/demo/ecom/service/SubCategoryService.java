package com.demo.ecom.service;

import com.demo.ecom.model.SubCategory;
import com.demo.ecom.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    public List<SubCategory> getSubCategoriesByCategory(int categoryId){
        return subCategoryRepository.getSubCategoriesByCategoryId(categoryId);
    }

    public SubCategory saveSubCategory(SubCategory subCategory){
        return subCategoryRepository.save(subCategory);
    }

    public List<SubCategory> getAllSubCategories(){
        return subCategoryRepository.findAll();
    }

    public Optional<SubCategory> findBySubCategory(Integer id){
        return subCategoryRepository.findById(id);
    }

    public Boolean existBySubCategory(Integer id){
        return subCategoryRepository.existsById(id);
    }
}
