package com.example.tema1.repository;

import com.example.tema1.model.Order;
import com.example.tema1.model.OrderStatus;
import com.example.tema1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findByOrderStatusNot(OrderStatus orderStatus);

   // List<Order> findByDateBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);

    List<Order> findByDataBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);

    List<Order> findByOrderStatus(OrderStatus orderStatus);
}
