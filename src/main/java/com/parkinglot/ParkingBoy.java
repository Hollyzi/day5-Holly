package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public void addParkingLots(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Ticket park(Car car) {
        List<ParkingLot> isAvailableParkingLots = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .collect(Collectors.toList());
        if (isAvailableParkingLots.size() != 0) {
            Ticket park = isAvailableParkingLots.get(0).park(car);
            return park;
        }
        return parkingLots.get(0).park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getNumber() == ticket.getParkingLotNumber())
                .findFirst()
                .orElse(null).fetch(ticket);
    }
}
