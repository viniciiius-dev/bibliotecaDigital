package com.viniciiiusdev.fateclivros.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;

    protected Reservation(){}

    public Reservation(User user, Book book, ReservationStatus reservationStatus, LocalDateTime expirationDate) {
        this.user = user;
        this.book = book;
        this.reservationStatus = reservationStatus;
        this.expirationDate = expirationDate;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    private enum availableStatus {
        ACTIVE, READY_FOR_PICKUP, FULFILLED, CANCELLED, COMPLETED
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    public long getReservationId() {
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
//        if (reservationStatus == ReservationStatus.COMPLETED){
//            throw new IllegalStateException("Não se pode cancelar uma reserva completada.");
//        }
//        reservationStatus = ReservationStatus.CANCELLED;
        // codigo com erro
    }
}
