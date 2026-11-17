package com.viniciiiusdev.fateclivros.controller;


import com.viniciiiusdev.fateclivros.dtos.AddReservationDTO;
import com.viniciiiusdev.fateclivros.dtos.ReservedBookDTO;
import com.viniciiiusdev.fateclivros.entity.Reservation;
import com.viniciiiusdev.fateclivros.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }



    @PostMapping
    public ReservedBookDTO addReservation(@RequestBody AddReservationDTO addReservationDTO){
        return reservationService.addReservation(addReservationDTO);
    }

    @GetMapping
    public List<ReservedBookDTO> getReservations(){
        return reservationService.getReservationsByUser();
    }

    @GetMapping("/{reservationId}")
    public ReservedBookDTO getReservation(@PathVariable Long reservationId) {
        return reservationService.getUserReservationById(reservationId);
    }

    @PatchMapping("/{reservationId}/cancel")
    public void cancelReservation(@PathVariable Long reservationId){
        reservationService.cancelReservation(reservationId);
    }
    @PatchMapping("/{reservationId}/pickup")
    public void pickupReservation(@PathVariable Long reservationId){
        reservationService.pickupReservation(reservationId);
    }
    @PatchMapping("/{reservationId}/return")
    public void returnReservations(@PathVariable Long reservationId){
        reservationService.returnReservation(reservationId);
    }
}
