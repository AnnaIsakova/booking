package me.annaisakova.booking.service;


import me.annaisakova.booking.model.BookingDate;
import me.annaisakova.booking.model.Hotel;
import me.annaisakova.booking.model.Room;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {

    // ===========HOTEL===========
    List<Hotel> getAllHotels();
    Optional<Hotel> getHotelById(@NotNull Long hotelId);
    Hotel createHotel(Hotel hotel);
    Hotel updateHotel(Hotel hotel);

    // ===========ROOM===========
    List<Room> getAllRooms(@NotNull Long hotelId);
    Optional<Room> getRoomById(@NotNull Long roomId);
    Room addRoom(@NotNull Long hotelId, Room room);
    Room updateRoom(Room room);
    List<Room> addRooms(@NotNull Long hotelId, List<Room> rooms);
    Room bookRoom(@NotNull Long roomId, BookingDate bookingDate);
}
