package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    public Iterable<User>getUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUser(Integer id){
        return userRepository.findById(id);
    }
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
    public void updateUser(User user){
        Optional<User> existingUser=userRepository.findById(user.getId());
        existingUser.equals(user);
    }
    public void createUser(User user){
        userRepository.save(user);
    }
    
}
