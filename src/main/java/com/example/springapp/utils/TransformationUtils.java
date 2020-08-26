package com.example.springapp.utils;

import com.example.springapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TransformationUtils {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User transformUserDTOtoUser(UserDTO userDTO) {
        User user = new User(userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getFirstName(), userDTO.getLastName(),  userDTO.getPhone(), userDTO.getCreatedDate(), userDTO.getRole());
        return user;
    }

    public Car transformCarDTOtoCar(CarDTO carDTO) {
        Car car = new Car(carDTO.getId(), carDTO.getAirCondition(), carDTO.getDoors(), carDTO.getGearbox(), carDTO.getSeats(), carDTO.getLargeBag(), carDTO.getSmallBag(), carDTO.getYear(), carDTO.getBrand(), carDTO.getModel(), carDTO.getPricePerDay());
        return car;
    }
}
