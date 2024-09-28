package com.springrest.demo.Service;

import com.springrest.demo.Entity.Booking;
import com.springrest.demo.Entity.Train;
import com.springrest.demo.Repository.BookingRepository;
import com.springrest.demo.Repository.TrainRepository;
import com.springrest.demo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public Booking bookSeat(Booking booking, String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new RuntimeException("Invalid token");
        }

        Long userId = jwtUtil.extractUserId(token);
        booking.setUserId(userId);

        Train train = trainRepository.findById(booking.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        if (train.getAvailableSeats() > 0) {
            train.setAvailableSeats(train.getAvailableSeats() - 1);
            trainRepository.save(train);

            booking.setBookingDate(LocalDateTime.now());
            booking.setSeatNumber(train.getTotalSeats() - train.getAvailableSeats());
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("No seats available");
        }
    }

    public Booking getBookingDetails(Long bookingId, String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new RuntimeException("Invalid token");
        }

        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}