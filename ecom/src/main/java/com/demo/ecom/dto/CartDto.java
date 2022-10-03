package com.demo.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;
}
