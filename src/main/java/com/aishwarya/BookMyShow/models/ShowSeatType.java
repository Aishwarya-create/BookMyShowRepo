package com.aishwarya.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    private Long price;
}


/* ShowSeatType Show
   1             1
   M             1

 ShowSeat
 Show_id, SeatType_id, Status
 2,        3            booked
 2,        4            available

 */
