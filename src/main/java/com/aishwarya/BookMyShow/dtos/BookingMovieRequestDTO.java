package com.aishwarya.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingMovieRequestDTO {
    // DTO for booking a movie (payload)
    private Long userId;
    private List<Long> showSeatIds;
    private Long showId;
}
