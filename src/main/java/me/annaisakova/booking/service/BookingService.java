package me.annaisakova.booking.service;


import me.annaisakova.booking.model.Booking;
import me.annaisakova.booking.model.Hotel;
import me.annaisakova.booking.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {

    // ===========HOTEL===========
    List<Hotel> getAllHotels();

    Optional<Hotel> getHotelById(Long hotelId);

    Hotel createHotel(Hotel hotel);

    Hotel updateHotel(Hotel hotel);

    // ===========ROOM===========
    List<Room> getAllRooms(Long hotelId);

    Optional<Room> getRoomById(Long hotelId, Long roomId);

    Room addRoom(Long hotelId, Room room);

    Room updateRoom(Room room);

    List<Room> addRooms(Long hotelId, List<Room> rooms);

    Booking bookRoom(Long hotelId, Long roomId, Booking booking);

    List<Booking> getAllBookingsForUser(Long userId);
}
