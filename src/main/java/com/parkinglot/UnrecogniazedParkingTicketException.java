package com.parkinglot;

public class UnrecogniazedParkingTicketException extends RuntimeException {

    public static final String UNRECOGNIAZED = "Unrecogniazed parking ticket.";

    public UnrecogniazedParkingTicketException() {
        super(UNRECOGNIAZED);
    }
}
