package com.example.fetchtakehomeexercise.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends RuntimeException {

    private String id;

    public NotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }
}
