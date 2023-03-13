package foodraport.foodraport.api.controller;

import foodraport.foodraport.api.model.User;
import foodraport.foodraport.service.UserService;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/user/{id}") // Get user by id
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id " + id + " not found.");
    }

    @GetMapping("/user") // Get all users
    public ResponseEntity<?> getAllUsers() {
        if (userService.getAllUsers().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PutMapping("/user/{id}") // Update users foods by id
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestParam String foods) {
        User user = userService.getUser(id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id " + id + " not found.");
        }
        
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
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/user/") // Add new user
    public ResponseEntity<?> addUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String email, @RequestParam String foods) {
        User user = userService.getUser(id);
        
        if (user != null){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User id " + id + " already in use. Please use another id.");
    }

        userService.addUser(id, name, email, foods);

        return ResponseEntity.status(HttpStatus.CREATED).body("User with id " + id + " created.");
    }

    @DeleteMapping("/user/{id}") // Delete user by id
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " not found.");
        }
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User with id " + id + " deleted.");
    }

    @GetMapping("/user/search") // Search foods by keyword, returns all foods lists that contain the keyword
    public ResponseEntity<?> searchFoods(@RequestParam String keyword){
        if (userService.searchFoods(keyword).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No foods found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.searchFoods(keyword));
    }
}