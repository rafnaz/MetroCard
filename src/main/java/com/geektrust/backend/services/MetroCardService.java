package com.geektrust.backend.services;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;

public interface MetroCardService {
    public MetroCard create(String cardNumber, int balance);
    public void recharge(MetroCard metroCard, int tripCharge, Passenger passenger);
    public void makePayment(MetroCard metroCard,int tripCharge,Passenger passenger);
}
