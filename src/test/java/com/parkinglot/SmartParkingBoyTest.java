package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmartParkingBoyTest {
    @Test
    void should_return_ticket_and_lotNumber1_when_two_parkingLots_emptyPosition_same_given_a_car() {
        //Given
        smartParkingBoy smartParkingBoy = new smartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(10, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        smartParkingBoy.addParkingLots(firstParkingLot);
        smartParkingBoy.addParkingLots(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = smartParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(ticket.getParkingLotNumber(), 1);
    }


}
