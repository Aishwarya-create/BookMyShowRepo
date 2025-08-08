package com.aishwarya.BookMyShow.service;

import com.aishwarya.BookMyShow.exceptions.SeatsNoLongerAvailableException;
import com.aishwarya.BookMyShow.exceptions.ShowNotFoundException;
import com.aishwarya.BookMyShow.exceptions.UserNotFoundException;
import com.aishwarya.BookMyShow.models.*;
import com.aishwarya.BookMyShow.repository.ShowRepository;
import com.aishwarya.BookMyShow.repository.ShowSeatRepository;
import com.aishwarya.BookMyShow.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculationService priceCalculationService;


    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculationService priceCalculationService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculationService = priceCalculationService;
    }

    public Ticket bookMovie(Long userId,
                            Long showId,
                            List<Long> showSeatIds) {

        // Validate user
        Optional<User> userOptional = userRepository.findById(userId);

        // If user does not exist, throw an exception
        //Redirect the user to sign up page
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        User user = userOptional.get();

        //Validate show
        Optional<Show> showOptional = showRepository.findById(showId);
        if (!showOptional.isPresent()) {
            throw new ShowNotFoundException("Show not found with ID: " + showId);
        }

        Show show = showOptional.get();

        // Validate show seats
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        if(showSeats.size() != showSeatIds.size()) {
            // Mismatch in the number of seats requested and available
            throw new RuntimeException("Please select valid show seats. Some of the selected seats are not available.");
        }

        for(ShowSeat showSeat : showSeats) {
            if(!showSeat.getSeatStatus().equals(SeatStatus.AVAILABLE)) {
                // If any of the selected seats are not available, throw an exception
                throw new SeatsNoLongerAvailableException(
                        "One or more of the selected seats are no longer available. Please select different seats."
                );
            }
        }



        /*
        Block the seats and then proceed with the payment.
        Take the lock on the DB here so that no other user can book the same seats
         */


        for(ShowSeat showSeat : showSeats) {
          if(showSeat.getSeatStatus().equals(SeatStatus.AVAILABLE)) {
              showSeat.setSeatStatus(SeatStatus.BLOCKED);
              showSeatRepository.save(showSeat);
          }else{
              throw new SeatsNoLongerAvailableException(
                      "One or more of the selected seats are no longer available. Please select different seats."
              );
          }
        }

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTicketStatus(TicketStatus.PENDING);
        ticket.setShowSeats(showSeats);
        ticket.setAmount(priceCalculationService.calculatePrice(showSeats, show));

        //start the payment flow
        //once the payment is done ,or it fails
        //call the payment service
        //come back and change the seat status accordingly
        // if the payment is successful, change the seat status to BOOKED
        // if the payment fails, change the seat status to AVAILABLE


        return ticket;
    }
}
