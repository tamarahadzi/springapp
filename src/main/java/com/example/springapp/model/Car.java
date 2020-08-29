package com.example.springapp.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_id_seq")
    @SequenceGenerator(name = "car_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "air_condition")
    private Boolean airCondition;

    @Column(name = "doors")
    private Integer doors;

    @Column(name = "gearbox")
    private String gearbox;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "large_bag")
    private Integer largeBag;

    @Column(name = "small_bag")
    private Integer smallBag;

    @Column(name = "year")
    private Integer year;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "price_per_day")
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

    public Car() {}

    public Car(Boolean airCondition, Integer doors, String gearbox, Integer seats, Integer largeBag, Integer smallBag, Integer year, String brand, String model, Float pricePerDay) {
        this.airCondition = airCondition;
        this.doors = doors;
        this.gearbox = gearbox;
        this.seats = seats;
        this.largeBag = largeBag;
        this.smallBag = smallBag;
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

}