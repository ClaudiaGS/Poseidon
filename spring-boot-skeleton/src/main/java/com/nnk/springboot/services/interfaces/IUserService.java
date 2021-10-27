package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.User;

import java.util.Optional;

public interface IUserService {
    public Iterable<User>getUsers();
    public Optional<User> getUser(Integer id);
    public void deleteUser(Integer id);
    public void updateUser(User user);
    public void createUser(User user);
}
