package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkinglotManagerTest {
    @Test
    void should_return_ticket_when_parkingboy_given_a_car_use_strategy() {
        //Given
        ParkinglotManager parkinglotManager = new ParkinglotManager();
        parkinglotManager.addParkingLot(new ParkingLot(10));
        ParkingStrategy parkingBoyStrategy = new ParkingBoyStrategy();
        //When
        parkinglotManager.setParkingStrategy(parkingBoyStrategy);
        Ticket ticket = parkinglotManager.parkingStrategyMethod(new Car(), parkinglotManager.getParkingLots());
        //Then
        assertNotNull(ticket);
    }
}
