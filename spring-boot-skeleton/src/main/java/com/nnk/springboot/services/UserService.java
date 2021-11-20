package com.nnk.springboot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.interfaces.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;
    
    private static final Logger logger = LogManager.getLogger("UserService");
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IUserService#getUsers()
     */
    @Override
    public Iterable<User>getUsers(){
        Iterable<User>userIterable;
        userIterable=userRepository.findAll();
        logger.info("User list is: "+asJsonString(userIterable));
        return userIterable;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IUserService#getUser(Integer)
     */
    @Override
    public Optional<User> getUser(Integer id){
        Optional<User>userOptional;
        userOptional=userRepository.findById(id);
        logger.info("User with id="+id+" is "+asJsonString(userOptional.get()));
        return userOptional;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IUserService#deleteUser(Integer)
     */
    @Override
    public void deleteUser(Integer id){
        logger.info("Deleting user with id="+id);
        userRepository.deleteById(id);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IUserService#updateUser(User)
     */
    @Override
    public void updateUser(User user){
        logger.info("Updating user "+user);
        userRepository.save(user);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IUserService#createUser(User)
     */
    @Override
    public void createUser(User user){
        logger.info("Creating user "+user);
        userRepository.save(user);
    }
    
    /**
     *
     * @param obj
     * @return String
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
