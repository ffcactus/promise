package com.promise.platform.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.UserRepository;

/**
 * User service.
 */
@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User getUser(String username)
    {
        Assert.hasText(username, "username must not be empty");
        return userRepository.findByUsername(username).get();
    }
}
