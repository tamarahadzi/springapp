package com.example.springapp.model;

import javax.persistence.Column;
import java.sql.Date;

public class ReservationDTO {

    private Long id;

    private Long carId;

    private Long userId;

    private String startDate;

    private String endDate;

    private String startPlace;

    private String endPlace;

    private Float price;

    private CarDTO carDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public ReservationDTO() {}

    public ReservationDTO(Long userId, Long carId, String startDate, String endDate, String startPlace, String endPlace, Float price, CarDTO car) {
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.price = price;
        this.carDTO = car;
    }
}
