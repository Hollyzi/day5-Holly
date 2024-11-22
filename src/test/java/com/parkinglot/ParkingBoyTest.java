package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
