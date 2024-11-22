package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
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
        return null;

    }

    public Car fetch(Ticket ticket) {
        if (parkingRecords.get(ticket) == null) {
            throw new Error("Unrecogniazed parking ticket");
        }
        return parkingRecords.remove(ticket);
    }
}
