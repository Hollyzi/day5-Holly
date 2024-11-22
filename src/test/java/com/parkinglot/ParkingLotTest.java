package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void should_return_ticket_when_park_given_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_given_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //When
        Car fetchCar = parkingLot.fetch(ticket);
        //Then
        assertEquals(car, fetchCar);
    }

    @Test
    void should_return_right_car_when_fetch_twice_car_given_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(0);
        Car firstCar = new Car();
        Ticket firstTicket = parkingLot.park(firstCar);
        Car secondCar = new Car();
        Ticket secondTicket = parkingLot.park(secondCar);
        //When
        Car fetchFirstCar = parkingLot.fetch(firstTicket);
        Car fetchSecondCar = parkingLot.fetch(secondTicket);
        //Then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secondCar, fetchSecondCar);

    }

    @Test
    void should_do_noting_when_fetch_given_a_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(0);
        Ticket wrongTicket = new Ticket();
        //When
        Car fetchResult = parkingLot.fetch(wrongTicket);
        //Then
        assertNull(fetchResult);

    }

    @Test
    void should_do_nothing_when_fetch_given_a_used_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        Ticket usedTicket = parkingLot.park(car);

        //When
        parkingLot.fetch(usedTicket);
        Car fetchResult = parkingLot.fetch(usedTicket);
        //Then
        assertNull(fetchResult);
    }

    @Test
    void should_do_nothing_when_parkinglot_is_full_given_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        //When
        Ticket nullTicket = parkingLot.park(car);
        //Then
        assertNull(nullTicket);
    }

    @Test
    void should_return_error_when_fetch_given_a_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot(0);
        Ticket wrongTicket = new Ticket();
        //When
        try {
            parkingLot.fetch(wrongTicket);
        } catch (Error error) {
            assertTrue(error.getMessage().contains("Unrecogniazed parking ticket"));
        }
        //Then
    }

    @Test
    void should_return_full_message_when_parklot_is_full_given_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        //When
        try {
            parkingLot.park(car);
        } catch (Error error) {
            assertTrue(error.getMessage().contains("The parkinglot is full"));
        }
    }

    private String systemOut() {
        return outContent.toString();
    }

}
