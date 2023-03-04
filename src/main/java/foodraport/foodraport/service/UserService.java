package foodraport.foodraport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import foodraport.foodraport.api.model.User;


@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();
        /*User user1 = new User(1,"Sampo", "sampo@email.com", "Kebab");
        User user2 = new User(2,"Inga", "inga@email.com", "Pizza");
        User user3 = new User(3,"Joonas", "joonas@email.com", "Kebab");
        User user4 = new User(4,"Santeri", "santeri@email.com","");
        userList.addAll(Arrays.asList(user1,user2,user3,user4));*/ //Use if you want to add test users
    }
    public User getUser(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User addUser(Integer id,String name, String email, String foods) {
        User newUser = new User(id, name, email, foods);
        userList.add(newUser);
        return newUser;
    }

    public void deleteUser(Integer id) {
        for (User user : userList) {
            if (user.getId() == id) {
                userList.remove(user);
                break;
            }
        }
    }
    public void updateUser(Integer id,String foods) {
        for (User user : userList) {
            if (user.getId() == id) {
                user.addFood(foods);
                break;
            }
        }
    }
}