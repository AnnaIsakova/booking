package me.annaisakova.booking.controller;


import me.annaisakova.booking.model.Hotel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/booking")
public class BookingController {

    @GetMapping
    public ResponseEntity<Hotel> getOne(){
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("name");
        hotel.setDescription("desc");
        return new ResponseEntity<Hotel>(hotel, HttpStatus.CREATED);
    }
}
