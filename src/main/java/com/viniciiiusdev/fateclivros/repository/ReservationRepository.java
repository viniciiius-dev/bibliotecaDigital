package com.viniciiiusdev.fateclivros.repository;

import com.viniciiiusdev.fateclivros.entity.Reservation;
import com.viniciiiusdev.fateclivros.entity.ReservationStatus;
import com.viniciiiusdev.fateclivros.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByUserAndReservationStatus(User user, ReservationStatus status);
    List<Reservation> findByUserOrderByExpirationDateAsc(User user);
    List<Reservation> findByUserOrderByExpirationDateDesc(User user);
}
