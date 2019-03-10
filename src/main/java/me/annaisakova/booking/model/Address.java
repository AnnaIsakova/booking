package me.annaisakova.booking.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@ToString
@Embeddable
public class Address {

    @NotNull
    @Column(nullable = false)
    private String country;

    @Column
    private String state;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false)
    private Integer number;

    @Column
    private Character letter;
}
