package com.springrest.demo.Controller;

import com.springrest.demo.Entity.Booking;
import com.springrest.demo.Security.JwtUtil;
import com.springrest.demo.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking bookSeat(@RequestBody Booking booking, @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7); // Remove "Bearer " prefix
        return bookingService.bookSeat(booking, jwtToken);
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingDetails(@PathVariable Long bookingId, @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7); // Remove "Bearer " prefix
        return bookingService.getBookingDetails(bookingId, jwtToken);
    }
}