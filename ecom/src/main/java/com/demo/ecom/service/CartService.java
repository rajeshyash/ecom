package com.demo.ecom.service;

import com.demo.ecom.dto.AddToCartDto;
import com.demo.ecom.dto.CartDto;
import com.demo.ecom.dto.CartItemDto;
import com.demo.ecom.model.Cart;
import com.demo.ecom.model.Product;
import com.demo.ecom.model.User;
import com.demo.ecom.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = buildDto(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto : cartItems) {
            totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        return new CartDto(cartItems, totalCost);
    }

    public static CartItemDto buildDto(Cart cart) {
        return new CartItemDto(cart);
    }

    public void updateCartItem(AddToCartDto cartDto, User user, Product product) {
        Optional<Cart> cartOpt = cartRepository.findById(cartDto.getId());
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            cart.setQuantity(cartDto.getQuantity());
            cart.setCreatedDate(new Date());
            cartRepository.save(cart);
        }

    }

    public int getCartItemQuantityById(Integer id){
        int quantity=0;
        Optional<Cart> cartOpt= cartRepository.findById(id);
        if (cartOpt.isPresent()) {
            quantity=cartOpt.get().getQuantity();
        }
        return quantity;
    }

    public void deleteCartItem(int id,User user) {
        cartRepository.deleteByIdAndUser(id,user);
    }

    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }

    public String validateRequest(AddToCartDto addToCartDto,Product product,User user) {
        StringBuilder errorMessage = new StringBuilder("");
        if (addToCartDto.getQuantity() <= 0) {
            errorMessage.append("Quantity can not be negative or 0 ");
        } else if (addToCartDto.getQuantity() > product.getStock()) {
            errorMessage.append("Quantity is more than available stock ");
        }
        if (addToCartDto.getProductId() <= 0) {
            errorMessage.append("Product Id is invalid ");
        }
        if (user == null) {
            errorMessage.append("UserId is not valid ");
        }
        return errorMessage.toString();
    }


}
