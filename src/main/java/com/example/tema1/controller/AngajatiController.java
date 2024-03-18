package com.example.tema1.controller;

import com.example.tema1.model.Order;
import com.example.tema1.service.MenuService;
import com.example.tema1.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/angajat")
@AllArgsConstructor
public class AngajatiController {
    private final OrderService orderService;
    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Map<String,Integer> order){
        return orderService.addOrder(order);
    }
    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable int id){
        return orderService.getOrder(id);
    }
    @GetMapping("/orders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }
    @GetMapping("/order/pregatire/{id}")
    public Order setStatusInPregatire(@PathVariable int id){
        return orderService.setStatusInPregatire(id);
    }
    @GetMapping("order/finalizare/{id}")
    public Order setStatusFinalizare(@PathVariable int id){
        return orderService.setStatusFinalizata(id);
    }
    @GetMapping("order/unfinalized")
    public List<Order> getUnfinalizedOrders() {
        return orderService.getUnfinalizedOrders();
    }
}
