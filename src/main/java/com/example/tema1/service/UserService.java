package com.example.tema1.service;

import com.example.tema1.model.Menu;
import com.example.tema1.model.User;
import com.example.tema1.repository.UserRepository;
import com.example.tema1.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public List<User> saveUsers(List<User> user){
        return userRepository.saveAll(user);
    }
    public List<User> getUsers(){
        return  userRepository.findAll();
    }
    public User getUserById(int id){
        return  userRepository.findById(id).orElse(null);
    }
    public User getUserByUsername(String username){
        return  userRepository.findByUsername(username);
    }
    public String deleteUser(int id){
        userRepository.deleteById(id);
        return "utilizatorul "+id+ " a fost sters";
    }
    public User updateUser(User user){
        User existingUser= userRepository.findById(user.getId()).orElse(null);
        if(existingUser != null) {
            existingUser.setNume(user.getNume());
            existingUser.setUsername(user.getUsername());
            existingUser.setParola(user.getParola());
            existingUser.setAdmin(user.isAdmin());
            return userRepository.save(existingUser);
        } else {

            throw new EntityNotFoundException("User not found with id: " + user.getId());
        }
    }
}
