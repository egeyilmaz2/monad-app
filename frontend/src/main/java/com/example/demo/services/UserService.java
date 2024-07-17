package com.example.demo.services;


import com.example.demo.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String backendUrl = "http://backend-service-url";

    public void addUser(User user) {
        restTemplate.postForEntity(backendUrl + "/addUser", user, Void.class);
    }

    public List<User> getAllUsers() {
        return Arrays.asList(restTemplate.getForObject(backendUrl + "/getAllUsers", User[].class));
    }
}
