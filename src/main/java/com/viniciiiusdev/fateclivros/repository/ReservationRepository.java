package com.viniciiiusdev.fateclivros.repository;

import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.entity.Reservation;
import com.viniciiiusdev.fateclivros.entity.ReservationStatus;
import com.viniciiiusdev.fateclivros.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);

    List<Reservation> findByUserAndReservationStatus(User user, ReservationStatus status);
    List<Reservation> findByUserOrderByExpirationDateAsc(User user);
    List<Reservation> findByUserOrderByExpirationDateDesc(User user);
    Optional<Reservation> findByReservationIdAndUser(Long reservationId, User user);
    boolean existsByUserAndBookAndReservationStatusIn(User user, Book book, Collection<ReservationStatus> statuses);
    Long countByUser(User user);
    Long countByUserAndReservationStatus(User user, ReservationStatus reservationStatus);
}
