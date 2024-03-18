package com.example.tema1.controller;

import com.example.tema1.model.Login;
import com.example.tema1.model.User;
import com.example.tema1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    private final UserService userService;
    @PostMapping("")
    public ResponseEntity<String> login(@RequestBody Login login){
    // Verificați credențialele
        User existingUser = userService.getUserByEmail(login.getEmail());
        if (existingUser == null) {
            System.out.println("Utilizatorul NU exista");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email incorect");
        }
        if (existingUser.getParola().equals(login.getParola())) {
            System.out.println("Utilizatorul exista");
            // Verificați rolul utilizatorului și redirecționați-l corespunzător
            if (existingUser.isAdmin()) {
                /*return ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", "/admin")
                        .body("redirect:/admin");*/
                return ResponseEntity.status(HttpStatus.FOUND).body("redirect:http://localhost:8080/admin");
            } else {
                /*return ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", "/angajat")
                        .body("redirect:/angajat");*/
                return ResponseEntity.status(HttpStatus.FOUND).body("redirect:http://localhost:8080/angajat");
            }
        }
        System.out.println("email sau parola incorecta");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parola incorecta");
    }
}
