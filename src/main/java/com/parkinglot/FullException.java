package com.parkinglot;

public class FullException extends RuntimeException {

    public static final String FULLEXCEPTION = "No available position";

    public FullException() {
        super(FULLEXCEPTION);
    }
}
