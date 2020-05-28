package com.g09.webshopspringboot.repository;

import com.g09.webshopspringboot.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

    List<OrderInfo> findAllByOrderId(Long orderId);
}
