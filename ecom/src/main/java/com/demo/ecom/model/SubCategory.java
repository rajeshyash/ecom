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
@Table(name = "SUBCATEGORY")
public class SubCategory {
    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "SubCategory name is mandatory")
    private String name;

    @NotNull(message = "Description is mandatory")
    private String description;

    @NotNull(message="Category Id is mandatory")
    private int categoryId;

}
