package me.annaisakova.booking.service.impl;


import lombok.RequiredArgsConstructor;
import me.annaisakova.booking.model.Booking;
import me.annaisakova.booking.model.Hotel;
import me.annaisakova.booking.model.Room;
import me.annaisakova.booking.repository.BookingRepository;
import me.annaisakova.booking.repository.HotelRepository;
import me.annaisakova.booking.repository.RoomRepository;
import me.annaisakova.booking.service.BookingService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;


    // ===========HOTEL===========
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> getHotelById(@NotNull Long hotelId) {
        return hotelRepository.findById(hotelId);
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // ===========ROOM===========
    @Override
    public Optional<Room> getRoomById(@NotNull Long hotelId, @NotNull Long roomId) {
        return roomRepository.findById(roomId)
                .filter(room -> room.getHotel().getId().equals(hotelId));
    }

    @Override
    public List<Room> getAllRooms(@NotNull Long hotelId) {
        return hotelRepository.findById(hotelId)
                .map(roomRepository::findAllByHotel)
                .orElseThrow(() -> new IllegalArgumentException("No hotel was found"));
    }

    @Override
    public Room addRoom(@NotNull Long hotelId, Room room) {
        return hotelRepository.findById(hotelId)
                .map(hotel -> {
                    room.setHotel(hotel);
                    return roomRepository.save(room);
                })
                .orElseThrow(() -> new IllegalArgumentException("No hotel was found"));
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> addRooms(@NotNull Long hotelId, List<Room> rooms) {
        return hotelRepository.findById(hotelId)
                .map(hotel -> {
                    rooms.forEach(room -> room.setHotel(hotel));
                    return roomRepository.saveAll(rooms);
                })
                .orElseThrow(() -> new IllegalArgumentException("No hotel was found"));
    }

    @Override
    public Booking bookRoom(@NotNull Long hotelId, @NotNull Long roomId, Booking booking) {
        return roomRepository.findById(roomId)
                .filter(room -> room.getHotel().getId().equals(hotelId))
                .map(room -> {
                    validateBookingDate(room, booking);
                    booking.setRoom(room);
                    return bookingRepository.save(booking);
                })
                .orElseThrow(() -> new IllegalArgumentException("No room was found"));
    }

    @Override
    public List<Booking> getAllBookingsForUser(Long userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    private void validateBookingDate(Room room, Booking booking) {
        Date from = booking.getBookedFrom();
        Date to = booking.getBookedTo();
        room.getBookings().forEach(date -> {
            if (from.after(to))
                throw new IllegalArgumentException("Date from cannot be after date to");
            else if (from.equals(date.getBookedFrom()) || to.equals(date.getBookedTo()))
                throw new IllegalArgumentException("Room is busy");
            else if (from.before(date.getBookedFrom()) && to.after(date.getBookedFrom()))
                throw new IllegalArgumentException("Room is busy");
            else if (from.after(date.getBookedFrom()) && from.before(date.getBookedTo()))
                throw new IllegalArgumentException("Room is busy");
        });
    }
}
