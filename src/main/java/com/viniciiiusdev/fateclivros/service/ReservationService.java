package com.viniciiiusdev.fateclivros.service;

import com.viniciiiusdev.fateclivros.dtos.AddReservationDTO;
import com.viniciiiusdev.fateclivros.entity.Reservation;
import com.viniciiiusdev.fateclivros.entity.ReservationStatus;
import com.viniciiiusdev.fateclivros.entity.User;

import java.util.List;

public interface ReservationService {
    void addReservation(AddReservationDTO addReservationDTO);
    void cancelReservation(Long reservationId);
    void completeReservation(Long reservationId);
    void markAsReadyForPickup(Long reservationId);
    Reservation getReservationById(Long reservationId);
    List<Reservation> getReservationsByUser(Long userId);
    List<Reservation> findByUserIdAndReservationStatus(Long userId, ReservationStatus status);
    List<Reservation> findByUserOrderByExpirationDateDesc(Long userId);
    List<Reservation> findByUserOrderByExpirationDateAsc(Long userId);

}
