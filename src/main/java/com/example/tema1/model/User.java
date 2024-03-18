package com.example.tema1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_user")
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nume;
    @Column(unique = true,nullable = false,length = 40)
    private String email;
   // @Column(unique = true,nullable = false,length = 40)
    private String parola;
    private boolean isAdmin;
}
