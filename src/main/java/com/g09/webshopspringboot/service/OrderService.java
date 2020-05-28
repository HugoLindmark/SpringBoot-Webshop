package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.*;
import com.g09.webshopspringboot.repository.OrderPurchaseRepository;
import com.g09.webshopspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderPurchaseRepository orderPurchaseRepository;
    private UserRepository userRepository;
    private CurrentSession currentSession;

    @Autowired
    public OrderService(OrderPurchaseRepository orderPurchaseRepository, UserRepository userRepository, CurrentSession currentSession) {
        this.orderPurchaseRepository = orderPurchaseRepository;
        this.userRepository = userRepository;
        this.currentSession = currentSession;
    }


    public List<OrderPurchase> SelectOrdersByUserId(Long userId) {
        return orderPurchaseRepository.findAllByUserId(userId);
    }

    public OrderPurchase addOrder() {
        User user = currentSession.getUser();
        int cartTotal = currentSession.calculateOrderTotal();
        List<OrderInfo> orderInfoList = new ArrayList<>();
        if (user.getTotalSpent() + cartTotal >= 500_000 && user.getRole() == Role.CUSTOMER) {
            user.setRole(Role.PREMIUM);
        }
        OrderPurchase order = new OrderPurchase(user, orderInfoList, LocalDate.now(), cartTotal);

        for (CartItem cartItem : currentSession.getCart()) {
            orderInfoList.add(new OrderInfo(cartItem.getRecord(), cartItem.getQuantity(), order));
        }
        for (int i = 0; i <100 ; i++) {
            int x = i * 2 / 2;
        }
        orderPurchaseRepository.save(order);
        userRepository.save(user);
        List<OrderPurchase> orders = orderPurchaseRepository.findAllByUserId(user.getId());
        user.setOrders(orders);
        currentSession.setUser(user);
        return order;
    }
}
