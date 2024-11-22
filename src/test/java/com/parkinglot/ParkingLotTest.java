package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNotNull(ticket);
    }

    //    @Test
//    void should_do_nothing_when_parklot_full_given_a_car(){
//        //Given
//        ParkingLot parkingLot=new ParkingLot();
//        //When
//
//        //Then
//
//    }
    @Test
    void should_return_car_when_fetch_given_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
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
        ParkingLot parkingLot = new ParkingLot();
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




}
