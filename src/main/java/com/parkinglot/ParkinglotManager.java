package com.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParkinglotManager {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingStrategy parkingStrategy;

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Ticket parkingStrategyMethod(Car car, List<ParkingLot> parkingLots) {
        return parkingStrategy.parkingStrategyMethod(car, parkingLots);
    }

    public Car fetchStrategyMethod(Ticket ticket, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getNumber() == ticket.getParkingLotNumber())
                .findFirst()
                .orElse(null).fetch(ticket);
    }

}

interface ParkingStrategy {
    public Ticket parkingStrategyMethod(Car car, List<ParkingLot> parkingLots);
}

class ParkingBoyStrategy implements ParkingStrategy {
    @Override
    public Ticket parkingStrategyMethod(Car car, List<ParkingLot> parkingLots) {
        List<ParkingLot> isAvailableParkingLots = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .collect(Collectors.toList());
        if (isAvailableParkingLots.size() != 0) {
            Ticket park = isAvailableParkingLots.get(0).park(car);
            return park;
        }
        return parkingLots.get(0).park(car);
    }
}

class SmartParkingBoyStrategy implements ParkingStrategy {
    @Override
    public Ticket parkingStrategyMethod(Car car, List<ParkingLot> parkingLots) {
        List<ParkingLot> isAvailableParkingLots = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .sorted(Comparator.comparing(ParkingLot::getEmptyPosition).reversed())
                .collect(Collectors.toList());
        if (isAvailableParkingLots.size() != 0) {
            Ticket park = isAvailableParkingLots.get(0).park(car);
            return park;
        }
        return parkingLots.get(0).park(car);
    }
}

class SuperSmartParkingBoyStrategy implements ParkingStrategy {
    @Override
    public Ticket parkingStrategyMethod(Car car, List<ParkingLot> parkingLots) {
        List<ParkingLot> isAvailableParkingLots = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getEmptyPosition() > 0)
                .sorted(Comparator.comparing(ParkingLot::getAvailablePositionRate).reversed())
                .collect(Collectors.toList());
        if (isAvailableParkingLots.size() != 0) {
            Ticket park = isAvailableParkingLots.get(0).park(car);
            return park;
        }
        return parkingLots.get(0).park(car);
    }
}
