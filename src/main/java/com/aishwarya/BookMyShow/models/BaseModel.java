package com.aishwarya.BookMyShow.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;

import java.util.Date;

/**
 * BaseModel serves as a base class for all models in the application.
 * It contains common fields such as id, createdAt, and updatedAt.
 */

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
