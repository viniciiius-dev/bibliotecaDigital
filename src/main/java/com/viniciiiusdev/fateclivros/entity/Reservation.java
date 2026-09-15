package com.viniciiiusdev.fateclivros.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    protected Reservation(){}

    public Reservation(User user, Book book) {
        this.user = user;
        this.book = book;
        this.reservationStatus = ReservationStatus.ACTIVE;
        this.expirationDate = LocalDateTime.now().plusDays(30);
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

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
        reservationStatus = ReservationStatus.CANCELLED;
    }

    public boolean canBeCancelled(){
        if (reservationStatus == ReservationStatus.CANCELLED || reservationStatus == ReservationStatus.COMPLETED || reservationStatus == ReservationStatus.FULFILLED){
            return false;
        }
        return true;
    }
}
