package com.example.tema1.service;

import com.example.tema1.model.Menu;
import com.example.tema1.model.Order;
import com.example.tema1.model.OrderStatus;
import com.example.tema1.repository.MenuRepository;
import com.example.tema1.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    public Order setStatusInPregatire(int orderId){
        Order order= orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(OrderStatus.COMANDA_IN_PREGATIRE);
        orderRepository.save(order);
        return order;
    }
    public Order setStatusFinalizata(int orderId){
        Order order= orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(OrderStatus.COMANDA_FINALIZATA);
        orderRepository.save(order);
        return order;
    }
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
    public List<Order> getUnfinalizedOrders() {
        return orderRepository.findByOrderStatusNot(OrderStatus.COMANDA_FINALIZATA);
    }
    public Order getOrder(int id){
        return orderRepository.findById(id).orElse(null);
    }
    public Order addOrder(Map<String,Integer> orderItems) {
        Map<Menu,Integer> orderList= goodOrder(orderItems);
        Order order= new Order();
        order.setOrderItemsList(orderList);
        order.setPriceOrder(calculateTotalPrice(order.getOrderItemsList()));
        // Set order status and timestamp
        order.setOrderStatus(OrderStatus.COMANDA_NOUA);
        order.setData(LocalDateTime.now());

        // Save order
        return orderRepository.save(order);
    }
    private Map<Menu,Integer> goodOrder(Map<String,Integer> orderItems){
        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("Order items map cannot be null or empty");
        }
        Map<Menu,Integer> orderList = new HashMap<>();
        for (Map.Entry<String,Integer> entry: orderItems.entrySet()){
            String menuItemString= entry.getKey();
            Menu menuItem = menuRepository.findByName(menuItemString);
            if(menuItem == null)
                throw new RuntimeException("Menu item not found");

            int quantity= entry.getValue();
            if(menuItem.getStoc()<quantity){
                throw new RuntimeException("Menu item out of stock: "+menuItem.getName());
            }
            orderList.put(menuItem,quantity);
        }
        return orderList;
    }
    private double calculateTotalPrice(Map<Menu,Integer> orderItems){
        double totalPrice=0;
        for(Map.Entry<Menu,Integer> entry: orderItems.entrySet()){
            Menu menuItem= entry.getKey();
            int quantity= entry.getValue();
            totalPrice+= menuItem.getPret()*quantity;
            menuItem.setStoc(menuItem.getStoc()-quantity);
            menuRepository.save(menuItem);
        }
        return totalPrice;
    }

    public List<Order> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByDataBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
    public Map<Menu, Integer> getMostOrderedProducts() {
        List<Order> completedOrders = orderRepository.findByOrderStatus(OrderStatus.COMANDA_FINALIZATA);
        Map<Menu, Integer> productQuantityMap = new HashMap<>();
        // Parcurgem comenzile finalizate
        for (Order order : completedOrders) {
            Map<Menu, Integer> orderItemsList = order.getOrderItemsList();
            // Parcurgem fiecare produs din comanda finalizată și actualizăm cantitatea totală
            for (Map.Entry<Menu, Integer> entry : orderItemsList.entrySet()) {
                Menu product = entry.getKey();
                int quantity = entry.getValue();

                // Adăugăm cantitatea la cantitatea totală a produsului
                productQuantityMap.merge(product, quantity, Integer::sum);
            }
        }
        // Sortăm HashMap-ul în funcție de cantitatea comandată și returnăm rezultatul
        Map<Menu, Integer> sortedMap = sortByValue(productQuantityMap);
        return sortedMap;
    }
    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}

