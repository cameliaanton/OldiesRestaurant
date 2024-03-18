package com.example.tema1.controller;

import com.example.tema1.model.Menu;
import com.example.tema1.model.Order;
import com.example.tema1.model.User;
import com.example.tema1.service.MenuService;
import com.example.tema1.service.OrderService;
import com.example.tema1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final UserService userService;
    private final MenuService menuService;
    private final OrderService orderService;
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users){
        return userService.saveUsers(users);
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        user.setId(id);
        return userService.updateUser(user);
    }
    @PostMapping("/addMenuItem")
    public Menu addMenuItem(@RequestBody Menu menuItem){
        return menuService.saveMenu(menuItem);
    }
    @GetMapping("/menu")
    public List<Menu> getMenu(){
        return menuService.getMenu();
    }
    @GetMapping("/menuItem/{id}")
    public Menu getMenuItemById(@PathVariable int id){
        return menuService.getProductById(id);
    }
    @DeleteMapping("/menuItem/{id}")
    public String deleteMenuItem(@PathVariable int id){
        return menuService.deleteProduct(id);
    }
    @PutMapping("/menuItem/{id}")
    public Menu updateMenuItem(@PathVariable int id, @RequestBody Menu menuItem){
        menuItem.setId(id);
        return menuService.updateMenu(menuItem);
    }
    @PutMapping("/menuItem/stock/{id}")
    public Menu updateMenuItemStock(@PathVariable int id, @RequestParam int stock) {
        return menuService.updateMenuItemStock(id, stock);
    }
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersBetweenDates(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate) {
        ///orders?startDate=2024-03-01&endDate=2024-03-19
        List<Order> orders = orderService.getOrdersBetweenDates(startDate, endDate);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/statistici")
    public Map<Menu, Integer> getStatistici(){
        return orderService.getMostOrderedProducts();
    }
}
