package com.demo.ecom.controller;

import com.demo.ecom.dto.ApiResponse;
import com.demo.ecom.exception.CustomValidationException;
import com.demo.ecom.model.Product;
import com.demo.ecom.service.ProductService;
import com.demo.ecom.service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @Autowired
    SubCategoryService subCategoryService;

    /**
     * @param product
     * description : Create new Product
     */
    @PostMapping("/products")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody Product product) {
        logger.info("Adding new product method call started.");
        if(!subCategoryService.existBySubCategory(product.getSubCategoryId())) {
            throw new CustomValidationException("Invalid SubCategory Id");
        }
        productService.saveProduct(product);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    /**
     *
     * description : Fetching all product
     */
    @GetMapping("/products")
    public List<Product> findAllProducts() {
        logger.info("Find all product method call started.");
        return productService.getProducts();
    }

    /**
     * @param : id
     * description : Fetching all product based on id
     */
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable int id) {
        logger.info("Find product based on id : " + id);
        return productService.getProductById(id);
    }

    /**
     * @param : subCategoryId
     * description : Fetching all product based on subCategoryId
     */
    @GetMapping("/products/product")
    public List<Product> findProductBySubCategory(@RequestParam Integer subCategoryId) {
        return productService.getProductBySubCategoryId(subCategoryId);
    }

    /**
     * @param : id
     * description : delete product based on id
     */
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

}
