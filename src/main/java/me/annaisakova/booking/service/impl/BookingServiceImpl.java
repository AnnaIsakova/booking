package me.annaisakova.booking.service.impl;


import me.annaisakova.booking.model.BookingDate;
import me.annaisakova.booking.model.Hotel;
import me.annaisakova.booking.model.Room;
import me.annaisakova.booking.repository.HotelRepository;
import me.annaisakova.booking.repository.RoomRepository;
import me.annaisakova.booking.service.BookingService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

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
        return hotelRepository.saveAndFlush(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.saveAndFlush(hotel);
    }

    // ===========ROOM===========
    @Override
    public Optional<Room> getRoomById(@NotNull Long roomId) {
        return roomRepository.findById(roomId);
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
                    return roomRepository.saveAndFlush(room);
                })
                .orElseThrow(() -> new IllegalArgumentException("No hotel was found"));
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public List<Room> addRooms(@NotNull Long hotelId, List<Room> rooms) {
        return hotelRepository.findById(hotelId)
                .map(hotel -> {
                    rooms.parallelStream().forEach(room -> room.setHotel(hotel));
                    return roomRepository.saveAll(rooms);
                })
                .orElseThrow(() -> new IllegalArgumentException("No hotel was found"));
    }

    @Override
    public Room bookRoom(@NotNull Long roomId, BookingDate bookingDate) {
        return roomRepository.findById(roomId)
                .map(room -> {
                    validateBookingDate(room, bookingDate);
                    room.getBookingDates().add(bookingDate);
                    return roomRepository.saveAndFlush(room);
                })
                .orElseThrow(() -> new IllegalArgumentException("No room was found"));
    }

    private void validateBookingDate(Room room, BookingDate bookingDate) {
        Date from = bookingDate.getBookedFrom();
        Date to = bookingDate.getBookedTo();
        room.getBookingDates().forEach(date -> {
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
