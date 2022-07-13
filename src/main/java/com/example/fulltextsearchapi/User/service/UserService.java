package com.example.fulltextsearchapi.User.service;

import com.example.fulltextsearchapi.User.models.Role;
import com.example.fulltextsearchapi.User.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
