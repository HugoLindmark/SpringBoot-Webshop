package com.g09.webshopspringboot.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SessionScope
@Component
public class CurrentSession {

    private String id;
    private User user;
    private List<CartItem> cart;

    public CurrentSession() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.id = UUID.randomUUID().toString();
        this.cart = new ArrayList<>();
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("total")
    public String formattedOrderTotal() {
        return FormatNumber.formatCurrency(calculateOrderTotal());
    }

    public int calculateOrderTotal(){
        int total = 0;
        for(CartItem cartItem : cart){
            total += cartItem.getQuantity()*cartItem.getRecord().getPrice();
        }
        if(user.getRole() == Role.PREMIUM){
            total = (int) (total*.9);
        }
        return total;
    }
}
