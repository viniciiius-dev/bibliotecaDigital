package com.viniciiiusdev.fateclivros.service;

import com.viniciiiusdev.fateclivros.dtos.AddReservationDTO;
import com.viniciiiusdev.fateclivros.entity.Reservation;

import java.util.List;

public interface ReservationService {
    void addReservation(AddReservationDTO addReservationDTO);
    void cancelReservation(Long reservationId);
    void completeReservation(Long reservationId);
    void markAsReadyForPickup(Long reservationId);
    Reservation getReservationById(Long reservationId);
    List<Reservation> getReservationsByUser(Long userId);
}
