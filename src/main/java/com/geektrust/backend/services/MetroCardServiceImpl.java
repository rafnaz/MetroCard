package com.geektrust.backend.services;


import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.repositories.MetroCardRepository;

public class MetroCardServiceImpl implements MetroCardService {

    private final StationService stationService;
    private final MetroCardRepository metroCardRepository;


    public MetroCardServiceImpl(StationService stationService, MetroCardRepository metroCardRepository) {
        this.stationService = stationService;
        this.metroCardRepository = metroCardRepository;
    }

    @Override
    public MetroCard create(String cardNumber, int balance) {
        Optional<MetroCard> existingCard = metroCardRepository.findByCardNumber(cardNumber);
   
        if(existingCard.isPresent()) {
            MetroCard foundCard = existingCard.get();
            return foundCard;
        }
        else {
            MetroCard newMetroCard = new MetroCard(cardNumber, balance);
            return metroCardRepository.save(newMetroCard);
        }
    }

    @Override
    public void recharge(MetroCard metroCard, int tripCharge, Passenger passenger) {

        int balance = metroCard.getBalance();
        int rechargeAmount = tripCharge - balance;
        metroCard.addAmount(rechargeAmount);
        stationService.collectServiceCharge(passenger, rechargeAmount);
        
    }

    @Override
    public void makePayment(MetroCard metroCard, int tripCharge, Passenger passenger) {
        metroCard.deductAmount(tripCharge);
        stationService.collectTravelCharge(passenger, tripCharge);
    }

    
}
