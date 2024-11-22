package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingBoy {
    private ParkingLot parkingLot=new ParkingLot(0);
    private List<ParkingLot> parkingLots=new ArrayList<>();

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void addParkingLots(ParkingLot parkingLot){
        parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        List<ParkingLot> isAvailableParkingLots = parkingLots.stream().filter(parkingLot -> parkingLot.getCapacity() <= 10).collect(Collectors.toList());
        return isAvailableParkingLots.get(0).park(car);
//        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLot.fetch(ticket);
    }
}
