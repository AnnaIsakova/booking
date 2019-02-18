package me.annaisakova.booking.service.impl;


import me.annaisakova.booking.model.Hotel;
import me.annaisakova.booking.repository.HotelRepository;
import me.annaisakova.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BookingServiceImpl implements BookingService{

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAll() {
        return null;
    }

    @Override
    public Hotel create(Hotel hotel) {
        return null;
    }
}
