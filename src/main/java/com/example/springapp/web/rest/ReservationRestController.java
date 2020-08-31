package com.example.springapp.web.rest;

import com.example.springapp.model.ReservationDTO;
import com.example.springapp.model.User;
import com.example.springapp.security.AuthoritiesConstants;
import com.example.springapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ReservationRestController {


    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservations")
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<Boolean> createReservation(@RequestBody ReservationDTO reservationDTO,
                                             Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                if (reservationService.createReservation(reservationDTO)) {
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

    @PutMapping("/reservations")
    public ResponseEntity<Boolean> updateReservation(@RequestBody ReservationDTO reservationDTO,
                                             Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                if (reservationService.updateReservation(reservationDTO)) {
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

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable("id") Long id,
                                         Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok().body(reservationService.getReservationById(id));
            }
            return ResponseEntity.badRequest().header("User is not authenticated").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        try {
            return ResponseEntity.ok().body(reservationService.getAllReservations());

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/myReservations")
    public ResponseEntity<List<ReservationDTO>> getMyReservations(Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                User user = (User) authentication.getPrincipal();
                return ResponseEntity.ok().body(reservationService.getMyReservations(user.getId()));
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Boolean> deleteReservations(@PathVariable("id") Long id,
                                             Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                if (reservationService.deleteReservationById(id)) {
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
