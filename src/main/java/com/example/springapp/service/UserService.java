package com.example.springapp.service;

import com.example.springapp.model.Role;
import com.example.springapp.model.User;
import com.example.springapp.model.UserDTO;
import com.example.springapp.repository.UserRepository;
import com.example.springapp.utils.TransformationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleService roleService;

    @Autowired
    public TransformationUtils transformationUtils;

    public boolean createUser(UserDTO userDTO) {
        try {
            userRepository.save(transformationUtils.transformUserDTOtoUser(userDTO, null));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateUser(UserDTO userDTO) {
        try {
            Optional<User> optionalUser = userRepository.findById(userDTO.getId());
            if (optionalUser.isPresent()) {
                userRepository.save(transformationUtils.transformUserDTOtoUser(userDTO, optionalUser.get().getPassword()));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public UserDTO getUserById(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return transformationUtils.transformUserToUserDTO(user.get());
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

    public List<UserDTO> getAllUsers(Long id) {
        return userRepository.getAllExceptLoggedUser(id).stream().map(user -> transformationUtils.transformUserToUserDTO(user)).collect(Collectors.toList());
    }
}
