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
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotNull(message="Category Name cannot be empty")
    private String name;

    @NotNull(message="Description can not be empty")
    private String description;

}
