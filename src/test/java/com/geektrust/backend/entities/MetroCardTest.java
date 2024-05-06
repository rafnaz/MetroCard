package com.geektrust.backend.entities;

import com.geektrust.backend.exceptions.InValidAmountException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MetroCardTest {
    
    @Test
    @DisplayName("addAmount method should add amount to MetroCard wallet")
    public void addAmount_shoudAddAmount_toMetroCard() {
        //Arrange
        int expectedBalance = 200;
        MetroCard metrocard = new MetroCard("MC1", 100);

        //Act
        metrocard.addAmount(100);
        int actualBalance = metrocard.getBalance();

        //Assert
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    @DisplayName("addAmount method should throw InValidAmount Exception if less than or equal to minAmount(0)")
    public void addAmount_shoudThrowInvalidAmountExcepion_forInvalidAmount() {
        //Arrange
        MetroCard metroCard = new MetroCard("MC1", 100);
        //Act and Assert
        Assertions.assertThrows(InValidAmountException.class,() -> metroCard.addAmount(-100)) ;
    }

    @Test
    @DisplayName("deductAmount method should deduct amount from Metrocard wallet")
    public void deductAmount_shoudDeductAmount_fromMetroCard() {
        //Arrange
        int expectedBalance = 100;
        MetroCard metroCard = new MetroCard("MC1", 200);

        //Act 
        metroCard.deductAmount(100);
        int actualBalance = metroCard.getBalance();

        //Assertion
        Assertions.assertEquals(expectedBalance, actualBalance);

    }

    @Test
    @DisplayName("deductAmount method should throw InValidAmount Exception if the amount deduct greater than balnace or <= zero")
    public void deductAmount_shoudThrowInvalidAmountExcepion_forInvalidAmount() {
        //Arrange
        MetroCard metroCard = new MetroCard("MC1", 100);
        //Act and Assert
        Assertions.assertThrows(InValidAmountException.class,() -> metroCard.deductAmount(200)) ;
    }

    @Test
    @DisplayName("hasSufficientBalance method should return true if the given amount is less than or equal to the balance of MetroCard")
    public void hasSufficientBalance_shouldReturnTrue_GivenAmount() {
        //Arrange 
        MetroCard metroCard = new MetroCard("MC1", 200);

        //Act and Assert
        Assertions.assertTrue(metroCard.hasSufficientBalance(100));
    }

    @Test
    @DisplayName("hasSufficientBalance method should return false if the given amount is greater than the balance of MetroCard")
    public void hasSufficientBalance_shouldReturnFalse_GivenAmount() {
        //Arrange
        MetroCard metroCard = new MetroCard("MC1", 200);

        //Act and Assert
        Assertions.assertFalse(metroCard.hasSufficientBalance(400));
    }
}
