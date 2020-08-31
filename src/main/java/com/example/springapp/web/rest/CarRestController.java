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

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class CarRestController {

    @Autowired
    private CarService carService;

    @PostMapping("/cars")
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

    @PutMapping("/cars")
    @Secured({AuthoritiesConstants.ADMIN})
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

    @GetMapping("/cars/{id}")
    @Secured({AuthoritiesConstants.ADMIN})
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

    @GetMapping("/cars")
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<List<CarDTO>> getAllCars(Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok().body(carService.getAllCars());
            }
            return ResponseEntity.badRequest().header("User is not authenticated").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/availableCars")
    @Secured({AuthoritiesConstants.USER})
    public ResponseEntity<List<CarDTO>> availableCars(@RequestParam("startDate") Date startDate,
                                                      @RequestParam("endDate") Date endDate,
                                                      Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok().body(carService.getAvailableCars(startDate, endDate));
            }
            return ResponseEntity.badRequest().header("User is not authenticated").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/cars/{id}")
    @Secured({AuthoritiesConstants.ADMIN})
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
