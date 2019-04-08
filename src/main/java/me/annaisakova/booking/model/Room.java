package me.annaisakova.booking.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;

    @Column
    private Byte numOfRooms;

    @NotNull
    @Column(nullable = false)
    private RoomType type;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    @ElementCollection(targetClass = RoomAccommodation.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "room_accommodation",
            joinColumns = @JoinColumn(name = "roomId")
    )
    private List<RoomAccommodation> accommodations;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;
}
