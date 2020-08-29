package com.example.springapp.utils;

import com.example.springapp.model.*;
import com.example.springapp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class TransformationUtils {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private CarRepository carRepository;

    public User transformUserDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(user.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setCreatedDate(userDTO.getCreatedDate());
        user.setRole(userDTO.getRole());
        return user;
    }

    public UserDTO transformUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public Car transformCarDTOtoCar(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setAirCondition(carDTO.getAirCondition());
        car.setDoors(carDTO.getDoors());
        car.setGearbox(carDTO.getGearbox());
        car.setSeats(carDTO.getSeats());
        car.setLargeBag(carDTO.getLargeBag());
        car.setSmallBag(carDTO.getSmallBag());
        car.setYear(carDTO.getYear());
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setPricePerDay(carDTO.getPricePerDay());

        return car;
    }

    public CarDTO transformCarToCarDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setAirCondition(car.getAirCondition());
        carDTO.setDoors(car.getDoors());
        carDTO.setGearbox(car.getGearbox());
        carDTO.setSeats(car.getSeats());
        carDTO.setLargeBag(car.getLargeBag());
        carDTO.setSmallBag(car.getSmallBag());
        carDTO.setYear(car.getYear());
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setPricePerDay(car.getPricePerDay());

        return carDTO;
    }

    public Reservation transformReservationDTOToReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setUserId(reservationDTO.getUserId());
        reservation.setCarId(reservationDTO.getCarId());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStartPlace(reservationDTO.getStartPlace());
        reservation.setEndPlace(reservationDTO.getEndPlace());
        reservation.setPrice(reservationDTO.getPrice());
        return reservation;
    }

    public ReservationDTO transformReservationToReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setUserId(reservation.getUserId());
        reservationDTO.setCarId(reservation.getCarId());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setStartPlace(reservation.getStartPlace());
        reservationDTO.setEndPlace(reservation.getEndPlace());
        reservationDTO.setPrice(reservation.getPrice());
        Optional<Car> car = carRepository.findById(reservation.getCarId());
        if (car.isPresent()) {
            reservationDTO.setCarDTO(transformCarToCarDTO(car.get()));
        }
        return reservationDTO;
    }
}
