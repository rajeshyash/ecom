package com.demo.ecom.service;

import com.demo.ecom.model.Product;
import com.demo.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> getProductBySubCategoryId(Integer subCategoryId) {
        return repository.findBySubCategoryId(subCategoryId);
    }

    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product deleted !! " + id;
    }

}
