package me.annaisakova.booking.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column
    private String name;

    @Column
    private String description;
}
