package com.monad.service.impl;
import com.monad.repository.entities.User;

import com.monad.repository.repositories.UserRepository;
import com.monad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByTcNumber(String tcNumber) {
        return userRepository.findByTcNumber(tcNumber);
    }

    @Override
    public void deleteByTcNumber(String tcNumber) {
        User user = userRepository.findByTcNumber(tcNumber);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}