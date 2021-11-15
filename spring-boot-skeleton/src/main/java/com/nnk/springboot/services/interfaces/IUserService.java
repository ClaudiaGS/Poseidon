package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.User;

import java.util.Optional;

public interface IUserService {
    
    /**
     *
     * @return Iterable<User>
     */
    public Iterable<User>getUsers();
    
    /**
     *
     * @param id
     * @return Optional<User>
     */
    public Optional<User> getUser(Integer id);
    
    /**
     *
     * @param id
     * @return void
     */
    public void deleteUser(Integer id);
    
    /**
     *
     * @param user
     * @return void
     */
    public void updateUser(User user);
    
    /**
     *
     * @param user
     * @return void
     */
    public void createUser(User user);
}
