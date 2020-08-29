package com.example.springapp.service;

import com.example.springapp.model.Car;
import com.example.springapp.model.CarDTO;
import com.example.springapp.model.User;
import com.example.springapp.model.UserDTO;
import com.example.springapp.repository.CarRepository;
import com.example.springapp.utils.TransformationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    public TransformationUtils transformationUtils;

    public boolean createCar(CarDTO carDTO) {
        try {
            carRepository.save(transformationUtils.transformCarDTOtoCar(carDTO));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateCar(CarDTO carDTO) {
        try {
            Optional<Car> optionalCar = carRepository.findById(carDTO.getId());
            if (optionalCar.isPresent()) {
                carRepository.save(transformationUtils.transformCarDTOtoCar(carDTO));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public CarDTO getCarById(Long id) {
        try {
            Optional<Car> car = carRepository.findById(id);
            if (car.isPresent()) {
                return transformationUtils.transformCarToCarDTO(car.get());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<CarDTO> getAllCars() {
        try {
            return carRepository.findAll().stream().map(car -> transformationUtils.transformCarToCarDTO(car)).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<CarDTO> getAvailableCars(Date startDate, Date endDate) {
        try {
            List<Car> unusedCars = carRepository.findUnusedCars();
            unusedCars.addAll(carRepository.findAvailableCars(startDate, endDate));
            return unusedCars.stream().map(car -> transformationUtils.transformCarToCarDTO(car)).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    public boolean deleteCarById(Long id) {
        try {
            carRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
