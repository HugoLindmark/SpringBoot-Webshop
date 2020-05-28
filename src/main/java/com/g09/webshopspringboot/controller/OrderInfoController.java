package com.g09.webshopspringboot.controller;

import com.g09.webshopspringboot.domain.OrderInfo;
import com.g09.webshopspringboot.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orderinfo")
public class OrderInfoController {

    private OrderInfoService orderInfoService;

    @Autowired
    public OrderInfoController(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    @GetMapping("{orderId}")
    public List<OrderInfo> getOrderInfoByOrderId(@PathVariable Long orderId) {
        return orderInfoService.selectAllOrderInfoByOrderId(orderId);
    }
}
