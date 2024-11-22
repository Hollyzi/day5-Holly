package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_return_correct_car_when_superSmartParkingBoy_fetch_twice_in_each_lot_given_ticket() {
        //Given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(1, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        superSmartParkingBoy.addParkingLots(firstParkingLot);
        superSmartParkingBoy.addParkingLots(secondParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();
        //When
        Ticket firstTicket = superSmartParkingBoy.park(firstCar);
        Ticket secondTicket = superSmartParkingBoy.park(secondCar);
        Car fetchFirstCar = superSmartParkingBoy.fetch(firstTicket);
        Car fetchSecondCar = superSmartParkingBoy.fetch(secondTicket);
        //Then
        assertEquals(fetchFirstCar, firstCar);
        assertEquals(fetchSecondCar, secondCar);
    }

    @Test
    void should_return_error_when_superSmartParkingBoy_fetch_given_a_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(10);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.addParkingLots(parkingLot);
        Ticket wrongTicket = new Ticket();
        //When
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class, () -> superSmartParkingBoy.fetch(wrongTicket));
        assertEquals("Unrecogniazed parking ticket.", exception.getMessage());
        //Then
    }

    @Test
    void should_return_error_when_superSmartParkingBoy_fetch_given_a_used_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(10);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.addParkingLots(parkingLot);
        Car car = new Car();
        Ticket usedTicket = parkingLot.park(car);
        superSmartParkingBoy.fetch(usedTicket);
        //When
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class, () -> superSmartParkingBoy.fetch(usedTicket));
        assertEquals("Unrecogniazed parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_full_message_when_two_parkingLots_both_full_superSmartParkingBoy_given_car() {
        //Given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(0, 1);
        ParkingLot secondParkingLot = new ParkingLot(0, 2);
        superSmartParkingBoy.addParkingLots(firstParkingLot);
        superSmartParkingBoy.addParkingLots(secondParkingLot);
        Car car = new Car();
        //When
        FullException fullException = assertThrows(FullException.class, () -> superSmartParkingBoy.park(car));
        assertEquals("No available position", fullException.getMessage());
    }
}
