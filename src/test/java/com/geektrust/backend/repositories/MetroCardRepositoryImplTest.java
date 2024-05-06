package com.geektrust.backend.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;

public class MetroCardRepositoryImplTest {
    private MetroCardRepository metroCardRepository;

    @BeforeEach
    public void setUp() {
        Map<String, MetroCard> cardMap = new HashMap<>();

        cardMap.put("1", new MetroCard("1","MC1",100));
        cardMap.put("2", new MetroCard("2","MC2",200));
        cardMap.put("3", new MetroCard("3","MC3",300));

        metroCardRepository = new MetroCardRepositoryImpl(cardMap);

    }

    @Test
    @DisplayName("save should create and return a MetroCard")
    public void saveMetroCard() {
        //Arrange
        MetroCard card4 = new MetroCard("MC4", 400);
        MetroCard expectedMetroCard = new MetroCard("4", "MC4", 400);

        //Act
        MetroCard actualCard = metroCardRepository.save(card4);

        //Assert
        Assertions.assertEquals(expectedMetroCard, actualCard);
    }

    @Test
    @DisplayName("findByCardNumber should return a card for given cardNumber")
    public void findByCardNumber_shouldReturnMetroCard_forGivenCardNumber() {
        //Arrange
        MetroCard expected = new MetroCard("2","MC2",200);

        //Act 
        MetroCard actual = metroCardRepository.findByCardNumber("MC2").get();

        //Assertion
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findByCardNumber should return a an empty card for cardNumber which not in the repo")
    public void findByCardNumber_shouldReturnEmpty_ifCardNumberNotFound() {
        //Arrange
        Optional<MetroCard> expected = Optional.empty();

        //Act 
        Optional<MetroCard> actual = metroCardRepository.findByCardNumber("MC6");

        //Assertion
        Assertions.assertEquals(expected, actual);
    }

}
