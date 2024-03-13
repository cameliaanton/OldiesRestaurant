package com.example.tema1.controller;

import com.example.tema1.model.Menu;
import com.example.tema1.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/menu")
@AllArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/addProduct")
    public Menu addProduct(@RequestBody Menu menu){
        return menuService.saveMenu(menu);
    }
    @PostMapping("/addProducts")
    public List<Menu> addProducts(@RequestBody List<Menu> menu){
        return menuService.saveMenus(menu);
    }
    @GetMapping("")
    public List<Menu> getProducts (){
        return menuService.getMenu();
    }
    @GetMapping("/product/{id}")
    public Menu getProductById (@PathVariable int id){
        return menuService.getProductById(id);
    }
    @GetMapping("/product/{name}")
    public Menu getProductByName (@PathVariable String name){
        return menuService.getProductByName(name);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct (@PathVariable int id){
        return menuService.deleteProduct(id);
    }
    @PutMapping("/update/{id}")
    public Menu updateProduct (@PathVariable int id,@RequestBody Menu menu){
        menu.setId(id);
        return menuService.updateMenu(menu);
    }


}
