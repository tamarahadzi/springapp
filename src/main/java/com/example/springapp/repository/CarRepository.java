package com.example.springapp.repository;

import com.example.springapp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    @Query(value = "select distinct * from car inner join reservation on car.id = reservation.car_id where not ((:startDate <= reservation.start_date and :endDate > reservation.start_date) or (:startDate > reservation.start_date and :startDate <= reservation.end_date))", nativeQuery = true)
    List<Car> findAvailableCars(@Param("startDate") Date startDate,
                                                  @Param("endDate") Date endDate);

    @Query(value = "select  * from car where car.id not in (select distinct car_id from reservation)", nativeQuery = true)
    List<Car> findUnusedCars();
}

