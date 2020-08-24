package com.example.springapp.service;

import com.example.springapp.model.Role;
import com.example.springapp.model.User;
import com.example.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleService roleService;

    public boolean createUser(String email, String password, String firstName, String lastName, Long roleId, String phone) {
        try {
            java.util.Date currentDate = new java.util.Date();
            Role role = roleService.getById(roleId);
            User newUser = new User(email, password, firstName, lastName, phone, new Date(currentDate.getTime()), role);
            userRepository.save(newUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateUser(Long id, String email, String password, String firstName, String lastName, String phone, Long roleId) {
        try {
            User user = userRepository.getOne(id);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setRole(roleService.getById(roleId));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getUserById(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return user.get();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
