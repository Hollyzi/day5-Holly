package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingBoy {
    private ParkingLot parkingLot = new ParkingLot(0);
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public static final String FULL_MESSAGE = "The parkinglot is full";

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void addParkingLots(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        List<ParkingLot> isAvailableParkingLots = parkingLots.stream().filter(parkingLot -> parkingLot.getCapacity() >0).collect(Collectors.toList());
        if (isAvailableParkingLots.size() != 0)
            return isAvailableParkingLots.get(0).park(car);
        throw new Error(FULL_MESSAGE);
//        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getNumber() == ticket.getParkingLotNumber())
                .findFirst()
                .orElse(null).fetch(ticket);
    }
}
