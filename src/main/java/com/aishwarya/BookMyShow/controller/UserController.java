package com.aishwarya.BookMyShow.controller;

import com.aishwarya.BookMyShow.dtos.ResponseStatus;
import com.aishwarya.BookMyShow.dtos.SignUpRequestDTO;
import com.aishwarya.BookMyShow.dtos.SignUpResponseDTO;
import com.aishwarya.BookMyShow.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO response = new SignUpResponseDTO();
        // Logic to save user details and create a response
        try {
            // Call the service to sign up the user
            response.setUser(userService.signUp(
                    signUpRequestDTO.getName(),
                    signUpRequestDTO.getEmail(),
                    signUpRequestDTO.getPassword()
            ));
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
