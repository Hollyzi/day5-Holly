package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkinglotManagerTest {
    @Test
    void should_return_ticket_when_parkingboy_given_a_car_use_strategy() {
        //Given
        ParkinglotManager parkinglotManager = new ParkinglotManager();
        parkinglotManager.addParkingLot(new ParkingLot(10));
        ParkingStrategy parkingBoyStrategy = new ParkingBoyStrategy();
        //When
        parkinglotManager.setParkingStrategy(parkingBoyStrategy);
        Ticket ticket = parkinglotManager.parkingStrategyMethod(new Car(), parkinglotManager.getParkingLots());
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_parking_boy_fetch_car_given_ticket_use_strategy() {
        //Given
        ParkinglotManager parkinglotManager = new ParkinglotManager();
        parkinglotManager.addParkingLot(new ParkingLot(10));
        ParkingStrategy parkingBoyStrategy = new ParkingBoyStrategy();
        //When
        parkinglotManager.setParkingStrategy(parkingBoyStrategy);
        Car car = new Car();
        Ticket ticket = parkinglotManager.parkingStrategyMethod(car, parkinglotManager.getParkingLots());
        Car fetchCar = parkinglotManager.fetchStrategyMethod(ticket, parkinglotManager.getParkingLots());
        //Then
        assertEquals(car, fetchCar);
    }

    @Test
    void should_return_right_car_when_parkingBoy_fetch_twice_car_given_ticket_use_strategy() {
        //Given
        ParkinglotManager parkinglotManager = new ParkinglotManager();
        ParkingLot firstParkinglot = new ParkingLot(10);
        ParkingLot secondParkinglot = new ParkingLot(10);
        parkinglotManager.addParkingLot(firstParkinglot);
        parkinglotManager.addParkingLot(secondParkinglot);
        ParkingStrategy parkingBoyStrategy = new ParkingBoyStrategy();
        //When
        parkinglotManager.setParkingStrategy(parkingBoyStrategy);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = parkinglotManager.parkingStrategyMethod(firstCar, parkinglotManager.getParkingLots());
        Ticket secondTicket = parkinglotManager.parkingStrategyMethod(secondCar, parkinglotManager.getParkingLots());
        Car fetchFirstCar = parkinglotManager.fetchStrategyMethod(firstTicket, parkinglotManager.getParkingLots());
        Car fetchSecondCar = parkinglotManager.fetchStrategyMethod(secondTicket, parkinglotManager.getParkingLots());
        //Then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secondCar, fetchSecondCar);
    }

    @Test
    void should_return_error_when_fetch_given_a_wrong_ticket_use_strategy() {
        //Given
        ParkinglotManager parkinglotManager = new ParkinglotManager();
        parkinglotManager.addParkingLot(new ParkingLot(10));
        ParkingStrategy parkingBoyStrategy = new ParkingBoyStrategy();
        //When
        parkinglotManager.setParkingStrategy(parkingBoyStrategy);
        Car car = new Car();
        parkinglotManager.parkingStrategyMethod(car, parkinglotManager.getParkingLots());
        //Then
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class, () -> parkinglotManager.fetchStrategyMethod(new Ticket(), parkinglotManager.getParkingLots()));
        assertEquals("Unrecogniazed parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_error_when_parkingboy_fetch_given_a_used_ticket_use_strategy() {
        //Given
        ParkinglotManager parkinglotManager = new ParkinglotManager();
        parkinglotManager.addParkingLot(new ParkingLot(10));
        ParkingStrategy parkingBoyStrategy = new ParkingBoyStrategy();
        //When
        parkinglotManager.setParkingStrategy(parkingBoyStrategy);
        Car car = new Car();
        Ticket ticket = parkinglotManager.parkingStrategyMethod(car, parkinglotManager.getParkingLots());
        parkinglotManager.fetchStrategyMethod(ticket, parkinglotManager.getParkingLots());
        //Then
        UnrecogniazedParkingTicketException exception
                = assertThrows(UnrecogniazedParkingTicketException.class, () -> parkinglotManager.fetchStrategyMethod(ticket, parkinglotManager.getParkingLots()));
        assertEquals("Unrecogniazed parking ticket.", exception.getMessage());
    }


    @Test
    void should_return_full_message_when_parkingBoy_is_full_given_car_use_strategy() {
        //Given
        ParkinglotManager parkinglotManager = new ParkinglotManager();
        parkinglotManager.addParkingLot(new ParkingLot(0));
        ParkingStrategy parkingBoyStrategy = new ParkingBoyStrategy();
        //When
        parkinglotManager.setParkingStrategy(parkingBoyStrategy);
        Car car = new Car();
        //Then
        FullException fullException = assertThrows(FullException.class, () -> parkinglotManager.parkingStrategyMethod(car, parkinglotManager.getParkingLots()));
        assertEquals("No available position", fullException.getMessage());
    }

    @Test
    void should_return_ticket_and_lotNumber1_when_first_parkingLot_is_not_full_given_a_car_use_strategy() {
        //Given
        ParkinglotManager parkinglotManager = new ParkinglotManager();
        ParkingLot firstParkingLot = new ParkingLot(10, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        parkinglotManager.addParkingLot(firstParkingLot);
        parkinglotManager.addParkingLot(secondParkingLot);
        ParkingStrategy parkingBoyStrategy = new ParkingBoyStrategy();
        //When
        parkinglotManager.setParkingStrategy(parkingBoyStrategy);
        Car car = new Car();
        Ticket ticket = parkinglotManager.parkingStrategyMethod(car, parkinglotManager.getParkingLots());
        //Then
        assertEquals(ticket.getParkingLotNumber(), 1);
    }

    @Test
    void should_return_ticket_and_lotNumber2_when_first_parkingLot_is_full_given_a_car_use_strategy() {
        //Given
        ParkinglotManager parkinglotManager = new ParkinglotManager();
        ParkingLot firstParkingLot = new ParkingLot(0, 1);
        ParkingLot secondParkingLot = new ParkingLot(10, 2);
        parkinglotManager.addParkingLot(firstParkingLot);
        parkinglotManager.addParkingLot(secondParkingLot);
        ParkingStrategy parkingBoyStrategy = new ParkingBoyStrategy();
        //When
        parkinglotManager.setParkingStrategy(parkingBoyStrategy);
        Car car = new Car();
        Ticket ticket = parkinglotManager.parkingStrategyMethod(car, parkinglotManager.getParkingLots());
        //Then
        assertEquals(ticket.getParkingLotNumber(), 2);
    }

}
