package com.example.springapp.model;

import javax.persistence.*;

public class CarDTO {

    private Long id;

    private Boolean airCondition;

    private Integer doors;

    private String gearbox;

    private Integer seats;

    private Integer largeBag;

    private Integer smallBag;

    private Integer year;

    private String brand;

    private String model;

    private Float pricePerDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(Boolean airCondition) {
        this.airCondition = airCondition;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getLargeBag() {
        return largeBag;
    }

    public void setLargeBag(Integer largeBag) {
        this.largeBag = largeBag;
    }

    public Integer getSmallBag() {
        return smallBag;
    }

    public void setSmallBag(Integer smallBag) {
        this.smallBag = smallBag;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

}
