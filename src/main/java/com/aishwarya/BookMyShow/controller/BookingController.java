package com.aishwarya.BookMyShow.controller;

import com.aishwarya.BookMyShow.dtos.BookingMovieRequestDTO;
import com.aishwarya.BookMyShow.dtos.BookingMovieResponseDTO;
import com.aishwarya.BookMyShow.dtos.ResponseStatus;
import com.aishwarya.BookMyShow.models.Ticket;
import com.aishwarya.BookMyShow.service.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingMovieResponseDTO bookMovie(BookingMovieRequestDTO bookingMovieRequestDTO) {

        // Logic to book the movie
        BookingMovieResponseDTO response = new BookingMovieResponseDTO();

        try {
            Ticket ticket = bookingService.bookMovie(
                    bookingMovieRequestDTO.getUserId(),
                    bookingMovieRequestDTO.getShowId(),
                    bookingMovieRequestDTO.getShowSeatIds()
            );
            response.setTicket(ticket);
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }
}
