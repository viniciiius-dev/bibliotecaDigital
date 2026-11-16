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
    @Column(nullable = false)
    private LocalDateTime expirationDate;

    public Reservation(User user, Book book) {
        this.user = user;
        this.book = book;
        this.reservationStatus = ReservationStatus.AGUARDANDO_RETIRADA;
        this.expirationDate = LocalDateTime.now().plusDays(7);
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
            throw new ReservationStatusException("Não é possível cancelar uma reserva no status: " + reservationStatus);
        }
        reservationStatus = ReservationStatus.CANCELADO;
    }

    public boolean canBeCancelled() {
        return reservationStatus == ReservationStatus.AGUARDANDO_RETIRADA;
    }

    public void returnBook(){
        if (reservationStatus != ReservationStatus.EM_ANDAMENTO) {
            throw new ReservationStatusException(
                    "Não é possível devolver uma reserva no status: " + reservationStatus
            );
        }

        reservationStatus = ReservationStatus.DEVOLVIDO;
    }
    public void pickup() {
        if (reservationStatus != ReservationStatus.AGUARDANDO_RETIRADA) {
            throw new ReservationStatusException(
                    "Não é possível retirar uma reserva no status: " + reservationStatus
            );
        }

        if (!expirationDate.isAfter(LocalDateTime.now())) {
            throw new ReservationStatusException("O prazo para retirada desta reserva expirou");
        }

        reservationStatus = ReservationStatus.EM_ANDAMENTO;
        expirationDate = LocalDateTime.now().plusDays(30);
    }




}
