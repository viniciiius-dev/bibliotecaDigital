package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.Exception.ResourceNotFoundException;
import com.viniciiiusdev.fateclivros.Exception.UserAlreadyHasExactReservationException;
import com.viniciiiusdev.fateclivros.dtos.AddReservationDTO;
import com.viniciiiusdev.fateclivros.dtos.BookStats;
import com.viniciiiusdev.fateclivros.dtos.ReservedBookDTO;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.entity.Reservation;
import com.viniciiiusdev.fateclivros.entity.ReservationStatus;
import com.viniciiiusdev.fateclivros.entity.User;
import com.viniciiiusdev.fateclivros.mapper.ReservationMapper;
import com.viniciiiusdev.fateclivros.repository.ReservationRepository;
import com.viniciiiusdev.fateclivros.service.BookService;
import com.viniciiiusdev.fateclivros.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final AuthenticatedUserService authenticatedUserService;
    private final BookService bookService;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    public ReservationServiceImpl(AuthenticatedUserService authenticatedUserService, BookService bookService, ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.authenticatedUserService = authenticatedUserService;
        this.bookService = bookService;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    // METODOS QUE PEGAM INFORMAÇÃO
    private Reservation getAuthenticatedUserReservation(Long reservationId) {
        User user = authenticatedUserService.getAuthenticatedUser();

        return reservationRepository
                .findByReservationIdAndUser(reservationId, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reserva não encontrada")
                );
    }


    @Override
    @Transactional(readOnly = true)
    public ReservedBookDTO getUserReservationById(Long reservationId) {
        Reservation reservation = getAuthenticatedUserReservation(reservationId);
        return reservationMapper.mapToReservedBook(reservation);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservedBookDTO> getReservationsByUser() {
        User user = authenticatedUserService.getAuthenticatedUser();
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return reservationMapper.mapToReservedBooks(reservations);
    }




    @Override
    @Transactional
    public ReservedBookDTO addReservation(AddReservationDTO addReservationDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();
        Book book = bookService.getBookById(addReservationDTO.bookId());

        List<ReservationStatus> activeStatus = List.of(ReservationStatus.AGUARDANDO_RETIRADA, ReservationStatus.EM_ANDAMENTO);
        if (reservationRepository.existsByUserAndBookAndReservationStatusIn(user, book, activeStatus)){
            throw new UserAlreadyHasExactReservationException("O usuário tem a mesma exata reserva ativa.");
        }
        book.reserve();

        Reservation reservation = new Reservation(user, book);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.mapToReservedBook(savedReservation);
    }






    @Override
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = getAuthenticatedUserReservation(reservationId);
        reservation.cancel();
        reservation.getBook().release();
    }

    @Override
    @Transactional
    public void pickupReservation(Long reservationId) {
        Reservation reservation = getAuthenticatedUserReservation(reservationId);

        reservation.pickup();
    }

    @Override
    @Transactional
    public void returnReservation(Long reservationId) {
        Reservation reservation = getAuthenticatedUserReservation(reservationId);

        reservation.returnBook();
        reservation.getBook().release();
    }




    // METODOS QUE PEGAM INFORMAÇÃO FILTRADA

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getUserReservationsByStatus(ReservationStatus status) {
        User user = authenticatedUserService.getAuthenticatedUser();

        return reservationRepository.findByUserAndReservationStatus(user, status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getUserReservationsOrderByExpirationDateDesc() {
        User user = authenticatedUserService.getAuthenticatedUser();

        return reservationRepository.findByUserOrderByExpirationDateDesc(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getUserReservationsOrderByExpirationDateAsc() {
        User user = authenticatedUserService.getAuthenticatedUser();

        return reservationRepository.findByUserOrderByExpirationDateAsc(user);
    }
    @Override
    public BookStats getBookStats(User user){
        return new BookStats(getNumberOfTotalReservations(user), getNumberOfPendingReservations(user));
    }
    @Override
    public Long getNumberOfTotalReservations(User user) {
        return reservationRepository.countByUser(user);
    }

    @Override
    public Long getNumberOfPendingReservations(User user) {
        ReservationStatus statusPendente = ReservationStatus.AGUARDANDO_RETIRADA;
        return reservationRepository.countByUserAndReservationStatus(user, statusPendente);
    }
}