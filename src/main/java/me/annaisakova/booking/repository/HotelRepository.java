package me.annaisakova.booking.repository;

import me.annaisakova.booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
