package com.demo.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartDto {

    private Integer id;

    @NotNull(message = "Product Id is mandatory")
    private Integer productId;

    @NotNull(message = "Quantity Id is mandatory")
    private Integer quantity;

}
