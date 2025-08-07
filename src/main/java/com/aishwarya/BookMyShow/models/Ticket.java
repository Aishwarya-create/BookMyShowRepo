package com.aishwarya.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel{
    private int ticketNumber;

    private TicketStatus ticketStatus;

    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    private User user;

    private Long amount;

    @OneToMany
    private List<Payment> payments;
}



// Ticket User => M : 1
// Ticket ShowSeat => M : M
/* ShowSeat Eg :

  Ticket_id = 78
  Show_id = 45 , Seat_id = 12
  Show_id = 45 , Seat_id = 13

  Suppose these seats got cancelled , they are available again for booking.

  Ticket_id = 80
    Show_id = 45 , Seat_id = 12
    Show_id = 45 , Seat_id = 13

 */
