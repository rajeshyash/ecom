package com.demo.ecom.controller;

import com.demo.ecom.model.User;
import com.demo.ecom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    /**
     *
     * description : Fetch all User
     */
    @GetMapping("/getUsers")
    public List<User> getAllUsers(){
        logger.info("Get All User Method started.");
        return userService.getAllUsers();
    }
    /**
     * @param user
     * description : Create new User
     */
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        logger.info("Create User method started.");
        return userService.saveUser(user);
    }
}
