package com.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy {
    @Override
    public Ticket park(Car car) {
        List<ParkingLot> isAvailableParkingLots = getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .sorted(Comparator.comparing(ParkingLot::getEmptyPosition).reversed())
                .collect(Collectors.toList());
        if (isAvailableParkingLots.size() != 0) {
            Ticket park = isAvailableParkingLots.get(0).park(car);
            return park;
        }
        return getParkingLots().get(0).park(car);
    }
}
