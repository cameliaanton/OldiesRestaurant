package com.example.tema1.repository;

import com.example.tema1.model.Menu;
import com.example.tema1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);
}
