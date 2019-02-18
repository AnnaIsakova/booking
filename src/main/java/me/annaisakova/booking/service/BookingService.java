package me.annaisakova.booking.service;


import me.annaisakova.booking.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    List<Hotel> getAll();
    Hotel create(Hotel hotel);
}
