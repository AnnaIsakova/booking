package me.annaisakova.booking.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode
@ToString
@Embeddable
public class BookingDate {

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
