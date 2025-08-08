package com.aishwarya.BookMyShow.service;

import com.aishwarya.BookMyShow.models.SeatType;
import com.aishwarya.BookMyShow.models.Show;
import com.aishwarya.BookMyShow.models.ShowSeat;
import com.aishwarya.BookMyShow.models.ShowSeatType;
import com.aishwarya.BookMyShow.repository.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculationService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculationService(ShowSeatTypeRepository showSeatRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public Long calculatePrice(List<ShowSeat> showSeats,
                              Show show) {
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        Long price = 0L;
        for (ShowSeat showSeat : showSeats) {
            //know the type of the seat
            SeatType seatType = showSeat.getSeat().getSeatType();
            for (ShowSeatType showSeatType : showSeatTypes) {
                if (showSeatType.getSeatType().equals(seatType)) {
                    //add the price of the seat type to the total price
                    price += showSeatType.getPrice();
                    break;
                }

            }
        }
        return price;

    }
}
