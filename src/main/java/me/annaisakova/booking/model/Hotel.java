package me.annaisakova.booking.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"rooms"})
@ToString(exclude = {"rooms"})
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @NotNull
    @Column(nullable = false)
    private Byte stars;

    @NotNull
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    @ElementCollection(targetClass = HotelAccommodation.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="hotel_accommodation")
    @Column(name="accommodation")
    private List<HotelAccommodation> accommodations;
}
