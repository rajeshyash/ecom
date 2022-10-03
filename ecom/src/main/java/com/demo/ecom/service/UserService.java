package com.demo.ecom.service;

import com.demo.ecom.model.User;
import com.demo.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


}
