package com.aishwarya.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat {
    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    private SeatStatus seatStatus;
}
