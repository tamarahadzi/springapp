package com.example.springapp.service;

import com.example.springapp.model.Car;
import com.example.springapp.model.CarDTO;
import com.example.springapp.model.Reservation;
import com.example.springapp.model.ReservationDTO;
import com.example.springapp.repository.ReservationRepository;
import com.example.springapp.utils.TransformationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    public TransformationUtils transformationUtils;

    public boolean createReservation(ReservationDTO reservationDTO) {
        try {
            reservationRepository.save(transformationUtils.transformReservationDTOToReservation(reservationDTO));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateReservation(ReservationDTO reservationDTO) {
        try {
            Optional<Reservation> optionalReservation = reservationRepository.findById(reservationDTO.getId());
            if (optionalReservation.isPresent()) {
                reservationRepository.save(transformationUtils.transformReservationDTOToReservation(reservationDTO));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public ReservationDTO getReservationById(Long id) {
        try {
            Optional<Reservation> reservation = reservationRepository.findById(id);
            if (reservation.isPresent()) {
                return transformationUtils.transformReservationToReservationDTO(reservation.get());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ReservationDTO> getAllReservations() {
        try {
            return reservationRepository.findAll().stream().map(reservation -> transformationUtils.transformReservationToReservationDTO(reservation)).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<ReservationDTO> getMyReservations(Long userId) {
        try {
            return reservationRepository.findByUserId(userId).stream().map(reservation -> transformationUtils.transformReservationToReservationDTO(reservation)).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean deleteReservationById(Long id) {
        try {
            reservationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
