package com.demo.ecom.repository;


import com.demo.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    public List<Product> findBySubCategoryId(int subCategoryId);

    Product findByName(String name);

}
