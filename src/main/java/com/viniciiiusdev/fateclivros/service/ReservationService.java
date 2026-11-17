package com.viniciiiusdev.fateclivros.service;

import com.viniciiiusdev.fateclivros.dtos.AddReservationDTO;
import com.viniciiiusdev.fateclivros.dtos.BookStats;
import com.viniciiiusdev.fateclivros.dtos.ReservedBookDTO;
import com.viniciiiusdev.fateclivros.entity.Reservation;
import com.viniciiiusdev.fateclivros.entity.ReservationStatus;
import com.viniciiiusdev.fateclivros.entity.User;

import java.util.List;

public interface ReservationService {
    ReservedBookDTO addReservation(AddReservationDTO addReservationDTO);
    void cancelReservation(Long reservationId);
    void pickupReservation(Long reservationId);
    void returnReservation(Long reservationId);
    List<ReservedBookDTO> getReservationsByUser();
    List<Reservation> getUserReservationsByStatus(ReservationStatus status);
    List<Reservation> getUserReservationsOrderByExpirationDateDesc();
    List<Reservation> getUserReservationsOrderByExpirationDateAsc();
    ReservedBookDTO getUserReservationById(Long reservationId);


    BookStats getBookStats(User user);

    Long getNumberOfTotalReservations(User user);

    Long getNumberOfPendingReservations(User user);
}