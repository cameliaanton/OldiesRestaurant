package com.example.tema1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="t_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //private String orderNumber;
    @ElementCollection
    private Map <Menu,Integer> orderItemsList;
    private double priceOrder;
    private OrderStatus orderStatus;
    private LocalDateTime data;


}
