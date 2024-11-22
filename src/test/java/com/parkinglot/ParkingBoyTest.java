package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    void should_return_ticket_when_parkingboy_given_a_car() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);

    }

    @Test
    void should_return_car_when_parking_boy_fetch_car_given_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);
        //Then
        assertEquals(car, fetchCar);

    }

    @Test
    void should_return_right_car_when_parkingBoy_fetch_twice_car_given_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car firstCar = new Car();
        Car secondCar = new Car();
        //When
        Ticket firstTicket = parkingBoy.park(firstCar);
        Ticket secondTicket = parkingBoy.park(secondCar);
        Car fetchFirstCar = parkingBoy.fetch(firstTicket);
        Car fetchSecondCar = parkingBoy.fetch(secondTicket);
        //Then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secondCar, fetchSecondCar);

    }

    @Test
    void should_return_error_when_fetch_given_a_wrong_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket wrongTicket = new Ticket();
        //When
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class, () -> parkingBoy.fetch(wrongTicket));
        assertEquals("Unrecogniazed parking ticket.", exception.getMessage());

        //Then

    }

    @Test
    void should_return_error_when_parkingboy_fetch_given_a_used_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket usedTicket = parkingBoy.park(car);
        parkingBoy.fetch(usedTicket);
        //When
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class, () -> parkingBoy.fetch(usedTicket));
        assertEquals("Unrecogniazed parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_full_message_when_parkingBoy_is_full_given_car() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot = new ParkingLot(0);
        parkingBoy.setParkingLot(parkingLot);
        Car car = new Car();
        //When
        try {
            parkingBoy.park(car);
        } catch (Error error) {
            assertTrue(error.getMessage().contains("The parkinglot is full"));
        }
    }

    @Test
    void should_return_ticket_and_lotNumber1_when_first_parkingLot_is_not_full_given_a_car() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(10, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        parkingBoy.addParkingLots(firstParkingLot);
        parkingBoy.addParkingLots(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(ticket.getParkingLotNumber(), 1);
    }

    @Test
    void should_return_ticket_and_lotNumber2_when_first_parkingLot_is_full_given_a_car() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(0, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        parkingBoy.addParkingLots(firstParkingLot);
        parkingBoy.addParkingLots(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        assertEquals(ticket.getParkingLotNumber(), 2);
    }

    @Test
    void should_return_correct_car_when_fetch_twice_in_each_lot_given_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(1, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        parkingBoy.addParkingLots(firstParkingLot);
        parkingBoy.addParkingLots(secondParkingLot);
        Car firstCar=new Car();
        Car secondCar=new Car();
        //When
        Ticket firstTicket=parkingBoy.park(firstCar);
        Ticket secondTicket=parkingBoy.park(secondCar);
        Car fetchFirstCar=parkingBoy.fetch(firstTicket);
        Car fetchSecondCar=parkingBoy.fetch(secondTicket);
        //Then
        assertEquals(fetchFirstCar,firstCar);
        assertEquals(fetchSecondCar,secondCar);
    }

    @Test
    void should_return_error_when_parkingBoy_fetch_given_a_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLots(parkingLot);
        Ticket wrongTicket = new Ticket();
        //When
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class,()->parkingBoy.fetch(wrongTicket));
        assertEquals("Unrecogniazed parking ticket.",exception.getMessage());
        //Then
    }

}
