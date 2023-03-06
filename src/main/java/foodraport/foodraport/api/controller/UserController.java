package foodraport.foodraport.api.controller;

import foodraport.foodraport.api.model.User;
import foodraport.foodraport.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public User getUser(@RequestParam Integer id) {
        User user = userService.getUser(id);
        if (user != null) {
            return user;
        }
        return null;
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestParam Integer id, @RequestParam String foods) {
        User user = userService.getUser(id);
        String existingFoods = userService.getUser(id).getFoods();
        String newFoods = foods;
        if (user != null) {
            existingFoods = user.getFoods();
        }
        if (existingFoods == null || existingFoods.isEmpty()) {
                foods = newFoods;}
        else {
                foods = newFoods + "," + existingFoods;
            }
        
        user.setFoods(foods);
        return user;
    }

    @PostMapping("/user/{id}")
    public User addUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String email, @RequestParam String foods) {
        User user = userService.addUser(id, name, email, foods);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
    }
}