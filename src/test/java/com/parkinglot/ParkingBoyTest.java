package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    void should_return_ticket_when_parkingboy_given_a_car(){
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);

    }

    @Test
    void should_return_car_when_parking_boy_fetch_car_given_ticket(){
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);
        //Then
        assertEquals(car,fetchCar);

    }

    @Test
    void should_return_right_car_when_parkingBoy_fetch_twice_car_given_ticket(){
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
        assertEquals(firstCar,fetchFirstCar);
        assertEquals(secondCar,fetchSecondCar);

    }

    @Test
    void should_return_error_when_fetch_given_a_wrong_ticket(){
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket wrongTicket=new Ticket();
        //When
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class,()->parkingBoy.fetch(wrongTicket));
        assertEquals("Unrecogniazed parking ticket.",exception.getMessage());

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
                = assertThrows(UnrecogniazedParkingTicketException.class,()->parkingBoy.fetch(usedTicket));
        assertEquals("Unrecogniazed parking ticket.",exception.getMessage());
    }

    @Test
    void should_return_full_message_when_parkingBoy_is_full_given_car() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot=new ParkingLot(10);
        parkingBoy.setParkingLot(parkingLot);
        Car car = new Car();
        //When
        try {
            parkingBoy.park(car);
        } catch (Error error) {
            assertTrue(error.getMessage().contains("The parkinglot is full"));
        }
    }

}
