package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final String FULL_MESSAGE = "The parkinglot is full";
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private Integer capacity;

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (this.capacity != 10) {
            this.capacity++;
            Ticket ticket = new Ticket();
            parkingRecords.put(ticket, car);
            return ticket;
        }
        throw new Error(FULL_MESSAGE);

    }

    public Car fetch(Ticket ticket) {
        if (!parkingRecords.containsKey(ticket)) {
            throw new UnrecogniazedParkingTicketException();
        }
        return parkingRecords.remove(ticket);
    }
}
