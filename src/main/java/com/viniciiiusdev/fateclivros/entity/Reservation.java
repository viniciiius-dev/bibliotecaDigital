package com.viniciiiusdev.fateclivros.entity;

import com.viniciiiusdev.fateclivros.Exception.ReservationStatusException;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    protected Reservation(){}
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private LocalDateTime expirationDate;

    public Reservation(User user, Book book) {
        this.user = user;
        this.book = book;
        this.reservationStatus = ReservationStatus.ATIVO;
    }


    public Long getReservationId() {
        return reservationId;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void cancel(){
        if (!canBeCancelled()) {
            throw new IllegalStateException("Não é possível cancelar uma reserva no status: " + reservationStatus);
        }
        reservationStatus = ReservationStatus.CANCELADO;
    }

    public boolean canBeCancelled(){
        return reservationStatus != ReservationStatus.CANCELADO && reservationStatus != ReservationStatus.COMPLETO && reservationStatus != ReservationStatus.LENDO;
    }

    public void complete(){
        if (!canBeCompleted()){
            throw new ReservationStatusException("Não é possível completar uma reserva no status: " + reservationStatus);
        }
        reservationStatus = ReservationStatus.COMPLETO;
    }

    public boolean canBeCompleted(){
        return reservationStatus == ReservationStatus.LENDO;
    }

    public void markAsReadyForPickup(){
        if (reservationStatus == ReservationStatus.ATIVO){
            reservationStatus = ReservationStatus.ESPERANDO_RETIRADA;
            expirationDate = LocalDateTime.now().plusDays(7);
        }
        else{
            throw new ReservationStatusException("Uma reserva precisa ter status ATIVO para seu status ser trocado para ESPERANDO_RETIRADA");
        }
    }
}
