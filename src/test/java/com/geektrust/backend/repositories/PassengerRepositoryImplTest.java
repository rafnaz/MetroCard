package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PassengerRepositoryImplTest {
    private PassengerRepository passengerRepository;

    @BeforeEach
    public void setUp() {

        Map<String, Passenger> passengerMap = new HashMap<>();

        MetroCard card1 = new MetroCard("1", "MC1", 100);
        MetroCard card2 = new MetroCard("2", "MC2", 200);
        passengerMap.put("1", new Passenger(card1, PassengerType.ADULT, "CENTRAL"));
        passengerMap.put("2", new Passenger(card2, PassengerType.KID, "AIRPORT"));

        passengerRepository = new PassengerRepositoryImpl(passengerMap);
    }

    @Test
    @DisplayName("save method should create and return a new Passenger")
    public void savePassenger() {

        //Arrange
        MetroCard card3 = new MetroCard("3","MC3", 300);
        Passenger passenger3 = new Passenger(card3, PassengerType.SENIOR_CITIZEN, "CENTRAL");
        Passenger expected = new Passenger("3",card3,PassengerType.SENIOR_CITIZEN,"CENTRAL");

        //Act
        Passenger actual = passengerRepository.save(passenger3);
        //Assert
        Assertions.assertEquals(expected, actual);

    }

    @Test
    @DisplayName("findByMetroCard should return a passenger for given card")
    public void findByCardNumber_shouldReturnPassenger_forGivenCard() {
        //Arrange
        MetroCard card2 = new MetroCard("1","MC1", 100);
        Passenger expected = new Passenger("2",card2,PassengerType.ADULT,"CENTRAL");

        //Act 
        Passenger actual = passengerRepository.findByMetroCard(card2).get();

        //Assertion
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findByMetroCard method should return empty if metroCard is not found")
    public void findByMetroCard_shouldReturnEmpty_IfMetroCardNotFound() {
        //Arrange
        MetroCard metroCard3 = new MetroCard("3", "MC3", 300);
        Optional<Passenger> expectedPassenger = Optional.empty();

        //Act
        Optional<Passenger> actualPassenger = passengerRepository.findByMetroCard(metroCard3);

        //Assert
        Assertions.assertEquals(expectedPassenger, actualPassenger);
    }
}
