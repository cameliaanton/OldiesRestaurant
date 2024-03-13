package com.example.tema1.controller;

import com.example.tema1.model.Menu;
import com.example.tema1.model.User;

import com.example.tema1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> user){
        return userService.saveUsers(user);
    }
    @GetMapping("")
    public List<User> getUsers (){
        return userService.getUsers();
    }
    @GetMapping("/id/{id}")
    public User getUserById (@PathVariable int id){
        return userService.getUserById(id);
    }
    @GetMapping("/username/{username}")
    public User getUserByUsername (@PathVariable String username){
        return userService.getUserByUsername(username);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser (@PathVariable int id){
        return userService.deleteUser(id);
    }
    @PutMapping("/update/{id}")
    public User updateProduct (@PathVariable int id, @RequestBody User user){
        user.setId(id);
        return userService.updateUser(user);
    }

}
