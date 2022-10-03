package com.demo.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Product Name is mandatory")
    private String name;

    @NotNull(message = "Product Stock is mandatory")
    private int stock;

    @NotNull(message = "Price is mandatory")
    private double price;

    @NotNull(message = "Sub Category is Mandatory")
    private int subCategoryId;
}
