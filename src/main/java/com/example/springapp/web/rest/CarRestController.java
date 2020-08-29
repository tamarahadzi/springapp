package com.example.springapp.web.rest;

import com.example.springapp.model.Car;
import com.example.springapp.model.CarDTO;
import com.example.springapp.model.User;
import com.example.springapp.model.UserDTO;
import com.example.springapp.security.AuthoritiesConstants;
import com.example.springapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class CarRestController {

    @Autowired
    private CarService carService;

    @PostMapping("/car")
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<Boolean> createCar(@RequestBody CarDTO carDTO,
                                              Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                if (carService.createCar(carDTO)) {
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

    @PutMapping("/car")
    public ResponseEntity<Boolean> updateCar(@RequestBody CarDTO carDTO,
                                              Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                if (carService.updateCar(carDTO)) {
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

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable("id") Long id,
                                      Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok().body(carService.getCarById(id));
            }
            return ResponseEntity.badRequest().header("User is not authenticated").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/car")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        try {
            return ResponseEntity.ok().body(carService.getAllCars());

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/car/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable("id") Long id,
                                              Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                if (carService.deleteCarById(id)) {
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
}
