package com.example.springapp.service;

import com.example.springapp.model.CustomUserDetails;
import com.example.springapp.model.Role;
import com.example.springapp.model.User;
import com.example.springapp.repository.RoleRepository;
import com.example.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUsers = userRepository.findByEmail(username);
        optionalUsers.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers.map(CustomUserDetails::new).get();
    }


    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

}
