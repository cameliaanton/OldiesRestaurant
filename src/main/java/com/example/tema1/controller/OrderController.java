package com.example.tema1.controller;

import com.example.tema1.model.Menu;
import com.example.tema1.model.Order;
import com.example.tema1.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("")
    public Order addOrder(@RequestBody Map<String,Integer>order){
        return orderService.addOrder(order);
    }
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable int id){
        return orderService.getOrder(id);
    }
    @GetMapping("")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }
    @GetMapping("/pregatire/{id}")
    public Order setStatusInPregatire(@PathVariable int id){
        return orderService.setStatusInPregatire(id);
    }
    @GetMapping("/finalizare/{id}")
    public Order setStatusFinalizare(@PathVariable int id){
        return orderService.setStatusFinalizata(id);
    }
    @GetMapping("/unfinalized")
    public List<Order> getUnfinalizedOrders() {
        return orderService.getUnfinalizedOrders();
    }

}
