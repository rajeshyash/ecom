package com.demo.ecom.repository;

import com.demo.ecom.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer> {

    public List<SubCategory> getSubCategoriesByCategoryId(int categoryId);
}
