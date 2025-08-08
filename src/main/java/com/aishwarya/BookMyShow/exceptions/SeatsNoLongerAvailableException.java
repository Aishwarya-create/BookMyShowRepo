package com.aishwarya.BookMyShow.exceptions;

public class SeatsNoLongerAvailableException extends RuntimeException
{
    public SeatsNoLongerAvailableException(String message) {
        super(message);
    }
}
