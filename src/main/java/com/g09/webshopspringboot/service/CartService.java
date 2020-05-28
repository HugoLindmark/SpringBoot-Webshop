package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.CartItem;
import com.g09.webshopspringboot.domain.CurrentSession;
import com.g09.webshopspringboot.domain.Record;
import com.g09.webshopspringboot.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private CurrentSession currentSession;
    private RecordRepository recordRepository;

    @Autowired
    public CartService(CurrentSession currentSession, RecordRepository recordRepository) {
        this.currentSession = currentSession;
        this.recordRepository = recordRepository;
    }


    public boolean addItemToCart(Long recordId) {
        List<CartItem> cart = currentSession.getCart();
        Optional<CartItem> cartItemOptional = cart.parallelStream().filter(cartItem -> cartItem.getRecord().getId() == recordId).findAny();
        CartItem cartItem = null;
        if (cartItemOptional.isPresent()) {
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return true;
        }
        else {
            cartItem = new CartItem(recordRepository.findById(recordId).get(),1);
            return currentSession.getCart().add(cartItem);
        }
    }

    public boolean removeCartItem(CartItem cartItem) {
        return currentSession.getCart().remove(cartItem);
    }

    public List<CartItem> getItemsInCart() {
        return currentSession.getCart();
    }

    public void clearCart(){
        currentSession.getCart().clear();
    }

}
