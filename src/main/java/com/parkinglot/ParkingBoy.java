package com.parkinglot;

public class ParkingBoy {
    private ParkingLot parkingLot=new ParkingLot(0);
    public Ticket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLot.fetch(ticket);
    }
}
