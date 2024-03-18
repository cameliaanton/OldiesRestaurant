package com.example.tema1.service;

import com.example.tema1.model.Menu;
import com.example.tema1.repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository repository;
    public Menu saveMenu(Menu menu){
       return repository.save(menu);
    }
    public List<Menu> saveMenus(List<Menu> menu){
        return repository.saveAll(menu);
    }
    public List<Menu> getMenu(){
        return  repository.findAll();
    }
    public Menu getProductById(int id){
        return  repository.findById(id).orElse(null);
    }
    public Menu getProductByName(String numePreparat){
        return  repository.findByName(numePreparat);
    }
    public String deleteProduct(int id){
        repository.deleteById(id);
        return "produsul "+id+ "a fost sters";
    }
    public Menu updateMenu(Menu menu){
        Menu existingMenu= repository.findById(menu.getId()).orElse(null);
        if(existingMenu != null) {
            existingMenu.setName(menu.getName());
            existingMenu.setStoc(menu.getStoc());
            existingMenu.setPret(menu.getPret());

            return repository.save(existingMenu);
        } else {

            throw new EntityNotFoundException("Menu not found with id: " + menu.getId());
        }
    }

    public Menu updateMenuItemStock(int id, int stock) {
        Menu existingMenu= repository.findById(id).orElseThrow(()->new RuntimeException("Menu item not found"));
        existingMenu.setStoc(stock);
        return repository.save(existingMenu);
    }
}
