package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_return_ticket_and_lotNumber2_when_lot2_has_more_emptyPosition_given_a_car() {
        //Given
        smartParkingBoy smartParkingBoy = new smartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(9, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        smartParkingBoy.addParkingLots(firstParkingLot);
        smartParkingBoy.addParkingLots(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = smartParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(ticket.getParkingLotNumber(), 2);
    }

    @Test
    void should_return_correct_car_when_smartParkingBoy_fetch_twice_in_each_lot_given_ticket() {
        //Given
        smartParkingBoy smartParkingBoy = new smartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(1, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        smartParkingBoy.addParkingLots(firstParkingLot);
        smartParkingBoy.addParkingLots(secondParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();
        //When
        Ticket firstTicket = smartParkingBoy.park(firstCar);
        Ticket secondTicket = smartParkingBoy.park(secondCar);
        Car fetchFirstCar = smartParkingBoy.fetch(firstTicket);
        Car fetchSecondCar = smartParkingBoy.fetch(secondTicket);
        //Then
        assertEquals(fetchFirstCar, firstCar);
        assertEquals(fetchSecondCar, secondCar);
    }

    @Test
    void should_return_error_when_smartParkingBoy_fetch_given_a_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(10);
        smartParkingBoy smartParkingBoy = new smartParkingBoy();
        smartParkingBoy.addParkingLots(parkingLot);
        Ticket wrongTicket = new Ticket();
        //When
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class, () -> smartParkingBoy.fetch(wrongTicket));
        assertEquals("Unrecogniazed parking ticket.", exception.getMessage());
        //Then
    }

    @Test
    void should_return_error_when_smartParkingBoy_fetch_given_a_used_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(10);
        smartParkingBoy smartParkingBoy = new smartParkingBoy();
        smartParkingBoy.addParkingLots(parkingLot);
        Car car = new Car();
        Ticket usedTicket = parkingLot.park(car);
        smartParkingBoy.fetch(usedTicket);
        //When
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class, () -> smartParkingBoy.fetch(usedTicket));
        assertEquals("Unrecogniazed parking ticket.", exception.getMessage());
    }


}
