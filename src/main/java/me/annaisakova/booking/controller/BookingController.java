package me.annaisakova.booking.controller;


import lombok.RequiredArgsConstructor;
import me.annaisakova.booking.model.Booking;
import me.annaisakova.booking.model.Hotel;
import me.annaisakova.booking.model.Room;
import me.annaisakova.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return new ResponseEntity<>(bookingService.getAllHotels(), HttpStatus.OK);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> getOneHotel(@PathVariable Long id) {
        return bookingService.getHotelById(id)
                .map(hotel -> new ResponseEntity<>(hotel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(bookingService.createHotel(hotel), HttpStatus.CREATED);
    }

    @PostMapping("/hotels/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        if (hotel.getId().equals(id)) {
            return new ResponseEntity<>(bookingService.updateHotel(hotel), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/hotels/{hotelId}/rooms")
    public ResponseEntity<List<Room>> getAllRooms(@PathVariable Long hotelId) {
        return new ResponseEntity<>(bookingService.getAllRooms(hotelId), HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}/rooms/{roomId}")
    public ResponseEntity<Room> getOneRoom(@PathVariable Long hotelId, @PathVariable Long roomId) {
        return bookingService.getRoomById(hotelId, roomId)
                .map(hotel -> new ResponseEntity<>(hotel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/hotels/{id}/rooms")
    public ResponseEntity<Room> createRoom(@PathVariable Long hotelId, @RequestBody Room room) {
        return new ResponseEntity<>(bookingService.addRoom(hotelId, room), HttpStatus.CREATED);
    }

    @PostMapping("/hotels/{hotelId}/rooms/{roomId}")
    public ResponseEntity<Booking> bookRoom(
            @PathVariable Long hotelId,
            @PathVariable Long roomId,
            @RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.bookRoom(hotelId, roomId, booking), HttpStatus.CREATED);
    }

    @GetMapping("/bookings/{userId}")
    public ResponseEntity<List<Booking>> getAllBookings(@PathVariable Long userId){
        return new ResponseEntity<>(bookingService.getAllBookingsForUser(userId), HttpStatus.OK);
    }
}


