package com.monad.service;


import com.monad.repository.entities.User;

import java.util.List;
import java.util.UUID;


public interface UserService {
    List<User> findAll();
    User findById(UUID id);
    User save(User user);
    void deleteById(UUID id);
    User findByTcNumber(String tcNumber);
    void deleteByTcNumber(String tcNumber);
}