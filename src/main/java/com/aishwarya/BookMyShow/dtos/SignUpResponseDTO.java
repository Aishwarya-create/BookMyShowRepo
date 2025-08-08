package com.aishwarya.BookMyShow.dtos;

import com.aishwarya.BookMyShow.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {
    private User user;
    private ResponseStatus responseStatus;
}
