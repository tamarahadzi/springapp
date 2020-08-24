package com.example.springapp.service;

import com.example.springapp.model.Role;
import com.example.springapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        Comparator<Role> compareRoleName = Comparator.comparing(Role::getName);
        List<Role> roles = roleRepository.findAll();
        roles.sort(compareRoleName);
        return roles;
    }

    public Role getById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        }
        return null;
    }
}
