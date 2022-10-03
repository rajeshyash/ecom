package com.demo.ecom.controller;

import com.demo.ecom.dto.AddToCartDto;
import com.demo.ecom.dto.ApiResponse;
import com.demo.ecom.dto.CartDto;
import com.demo.ecom.exception.CustomValidationException;
import com.demo.ecom.model.Product;
import com.demo.ecom.model.User;
import com.demo.ecom.service.CartService;
import com.demo.ecom.service.ProductService;
import com.demo.ecom.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.ValidationException;


@Api(value="APIs for Cart Service")
@RestController
@RequestMapping(value="/cart",produces ={"application/json"})
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @ApiOperation(value = "Add Items to Cart",response = ApiResponse.class)
    @PostMapping("/")
    public ResponseEntity<ApiResponse> addToCart(@Valid @RequestBody AddToCartDto addToCartDto, @NotBlank  @RequestParam Integer userId) throws ValidationException {
        User user = userService.getUserById(userId);
        Product product = productService.getProductById(addToCartDto.getProductId());
        String error = cartService.validateRequest(addToCartDto,product,user);
        if(!error.isEmpty()){
            throw new CustomValidationException(error);
        }
        cartService.addToCart(addToCartDto, product, user);
        int stock=product.getStock()-addToCartDto.getQuantity();
        product.setStock(stock);
        productService.updateProduct(product);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("userId") int userId) {
        User user = userService.getUserById(userId);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> updateCartItem(@Valid @RequestBody AddToCartDto cartDto,
                                                      @NotBlank @RequestParam("userId") int userId) {
        User user = userService.getUserById(userId);
        Product product = productService.getProductById(cartDto.getProductId());
        String error = cartService.validateRequest(cartDto,product,user);
        if(!error.isEmpty()){
            throw new CustomValidationException(error);
        }
        int currentStock= cartService.getCartItemQuantityById(cartDto.getId());
        int stock = product.getStock();
        stock = stock+currentStock-cartDto.getQuantity();
        cartService.updateCartItem(cartDto, user,product);
        product.setStock(stock);
        productService.saveProduct(product);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int cartItemId, @RequestParam("userId") int userId)  {
        User user = userService.getUserById(userId);
        cartService.deleteCartItem(cartItemId,user);
        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }


}
