package com.viniciiiusdev.fateclivros.repository;


import com.viniciiiusdev.fateclivros.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
