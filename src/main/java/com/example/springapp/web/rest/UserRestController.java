package com.example.springapp.web.rest;

import com.example.springapp.model.User;
import com.example.springapp.repository.UserRepository;
import com.example.springapp.security.AuthoritiesConstants;
import com.example.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    @Autowired
    public UserService userService;


    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<Boolean> createUser(@RequestParam("email") String email,
                                              @RequestParam("password") String password,
                                              @RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName,
                                              @RequestParam("phone") String phone,
                                              @RequestParam("role") Long roleId,
                                              Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                if (userService.createUser(email, passwordEncoder.encode(password), firstName, lastName, roleId,  phone)) {
                    return ResponseEntity.ok().body(true);
                } else {
                    return ResponseEntity.badRequest().build();
                }
            }
            return ResponseEntity.badRequest().header("User is not authenticated").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/user")
    public ResponseEntity<Boolean> updateUser(@RequestParam("id") Long id,
                                              @RequestParam("email") String email,
                                              @RequestParam("password") String password,
                                              @RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName,
                                              @RequestParam("phone") String phone,
                                              @RequestParam("role") Long roleId,
                                              Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                if (userService.updateUser(id, email, passwordEncoder.encode(password), firstName, lastName, phone, roleId)) {
                    return ResponseEntity.ok().body(true);
                } else {
                    return ResponseEntity.badRequest().build();
                }
            }
            return ResponseEntity.badRequest().header("User is not authenticated").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id,
                                        Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok().body(userService.getUserById(id));
            }
            return ResponseEntity.badRequest().header("User is not authenticated").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok().body(userService.getAllUsers());

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id,
                                              Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                if (userService.deleteUserById(id)) {
                    return ResponseEntity.ok().body(true);
                } else {
                    return ResponseEntity.badRequest().build();
                }
            }
            return ResponseEntity.badRequest().header("User is not authenticated").build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    //@Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<User> login(@RequestParam("email") String email,
                                      @RequestParam("password") String password) {
        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}

