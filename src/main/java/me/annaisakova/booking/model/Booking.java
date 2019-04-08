package me.annaisakova.booking.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

    @NotNull
    @Column(nullable = false)
    private Date bookedFrom;

    @NotNull
    @Column(nullable = false)
    private Date bookedTo;

    @NotNull
    private Long userId;

    @Column
    private String comment;
}
