package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.OrderInfo;
import com.g09.webshopspringboot.repository.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoService {

    private OrderInfoRepository orderInfoRepository;

    @Autowired
    public OrderInfoService(OrderInfoRepository orderInfoRepository) {
        this.orderInfoRepository = orderInfoRepository;
    }

    public List<OrderInfo> selectAllOrderInfoByOrderId(Long orderId) {
        return orderInfoRepository.findAllByOrderId(orderId);
    }
}
