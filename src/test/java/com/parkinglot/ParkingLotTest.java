package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
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
        Car car = new Car();
        Ticket correctTicket = parkingLot.park(car);
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


}
