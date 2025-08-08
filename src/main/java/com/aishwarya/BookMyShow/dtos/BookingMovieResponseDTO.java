package com.aishwarya.BookMyShow.dtos;


import com.aishwarya.BookMyShow.models.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingMovieResponseDTO {
    private Ticket ticket;
    private ResponseStatus responseStatus;
}
