package com.geektrust.backend.services;

import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.exceptions.MetroCardNotFoundException;
import com.geektrust.backend.repositories.MetroCardRepository;
import com.geektrust.backend.repositories.PassengerRepository;

public class PassengerServiceImpl implements PassengerService{

    private final StationService stationService;
    private final MetroCardService metroCardService;
    private final MetroCardRepository metroCardRepository;
    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(StationService stationService, MetroCardService metroCardService, MetroCardRepository metroCardRepository, PassengerRepository passengerRepository) {
        this.stationService = stationService;
        this.metroCardService = metroCardService;
        this.metroCardRepository = metroCardRepository;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Passenger create(String cardNumber, PassengerType passengerType,String staringStation) {
        MetroCard metroCard = metroCardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new MetroCardNotFoundException());
        Optional<Passenger> passenger = passengerRepository.findByMetroCard(metroCard);

        if(passenger.isPresent()) {
            Passenger existingPassenger = passenger.get();
            existingPassenger.setStartingStation(staringStation);
            return existingPassenger;
        }else {
            Passenger newPassenger = new Passenger(metroCard, passengerType, staringStation);
            return passengerRepository.save(newPassenger);
        }
    }

    @Override
    public void travel(Passenger passenger) {
        MetroCard metroCard = passenger.getMetroCard();
        passenger.updateTripType();
        int tripCharge = stationService.getTravelCharge(passenger);

        if(metroCard.hasSufficientBalance(tripCharge))
            metroCardService.makePayment(metroCard, tripCharge, passenger);
        else
        {
            metroCardService.recharge(metroCard, tripCharge, passenger);
            metroCardService.makePayment(metroCard, tripCharge, passenger);
        }
        stationService.registerPassengerOnBoard(passenger);
    }
        

    
}
