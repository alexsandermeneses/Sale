package org.example.rest;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String ErrorMessage){
        this.errors = Arrays.asList(ErrorMessage);
    }


}
