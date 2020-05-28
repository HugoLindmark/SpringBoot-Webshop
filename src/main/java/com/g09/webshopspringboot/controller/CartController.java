package com.g09.webshopspringboot.controller;

import com.g09.webshopspringboot.domain.CartItem;
import com.g09.webshopspringboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//en controllerklass för att kunna lagra produkter i cart
@RestController
@RequestMapping("cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @CrossOrigin
    @PostMapping("add/{id}")
    public boolean addToCart(@PathVariable Long id) {
       return cartService.addItemToCart(id);
    }

    @DeleteMapping("remove")
    public boolean removeFromCart(CartItem cartItem) {
        return cartService.removeCartItem(cartItem);
    }

    @GetMapping
    public List<CartItem> getCart() {
        return cartService.getItemsInCart();
    }

    @GetMapping("empty")
    public void emptyCart(){
        cartService.clearCart();
    }

}
