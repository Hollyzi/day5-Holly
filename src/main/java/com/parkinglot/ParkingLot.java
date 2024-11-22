package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    private Integer capacity;
    private Integer emptyPosition;
    private Integer number;


    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
        this.emptyPosition=capacity;
    }

    public ParkingLot(Integer capacity, Integer number) {
        this.capacity = capacity;
        this.number = number;
        this.emptyPosition=capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getEmptyPosition() {
        return emptyPosition;
    }

    public Ticket park(Car car) {
        if (capacity == 0||emptyPosition==0)
            throw new FullException();
        this.emptyPosition--;
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        ticket.setParkingLotNumber(this.number);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if (!parkingRecords.containsKey(ticket)) {
            throw new UnrecogniazedParkingTicketException();
        }
        return parkingRecords.remove(ticket);
    }
}
