package com.dicanxan.springsecurityapp.service;


import com.dicanxan.springsecurityapp.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}