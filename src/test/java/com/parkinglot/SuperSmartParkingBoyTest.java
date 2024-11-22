package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperSmartParkingBoyTest {
    @Test
    void should_return_ticket_and_lotNumber1_when_two_parkingLots_availablePositionRate_same_given_a_car() {
        //Given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(10, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        superSmartParkingBoy.addParkingLots(firstParkingLot);
        superSmartParkingBoy.addParkingLots(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = superSmartParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(ticket.getParkingLotNumber(), 1);
    }

    @Test
    void should_return_ticket_and_lotNumber2_when_lotNumber2_availablePositionRate_higher_given_a_car() {
        //Given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(10, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        superSmartParkingBoy.addParkingLots(firstParkingLot);
        superSmartParkingBoy.addParkingLots(secondParkingLot);
        Car car = new Car();
        Car eliminateRateCar = new Car();
        superSmartParkingBoy.park(eliminateRateCar);
        //When
        Ticket ticket = superSmartParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(ticket.getParkingLotNumber(), 2);
    }
}
