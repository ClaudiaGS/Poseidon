package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public Iterable<User>getUsers(){
        return userRepository.findAll();
    }
    @Override
    public Optional<User> getUser(Integer id){
        return userRepository.findById(id);
    }
    @Override
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
    @Override
    public void updateUser(User user){
        userRepository.save(user);
    }
    @Override
    public void createUser(User user){
        userRepository.save(user);
    }
    
}
