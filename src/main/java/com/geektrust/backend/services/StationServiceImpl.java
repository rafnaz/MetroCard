package com.geektrust.backend.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.geektrust.backend.dtos.CollectionSummary;
import com.geektrust.backend.dtos.PassengerSummary;
import com.geektrust.backend.dtos.PassengerTypeSummary;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.entities.TravelCharge;
import com.geektrust.backend.entities.TripType;
import com.geektrust.backend.exceptions.StationNotFoundException;
import com.geektrust.backend.repositories.StationRepository;

public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station create(String stationName) {
        return stationRepository.findByName(stationName)
        .orElseGet(() -> stationRepository.save(new Station(stationName)));

    }

    @Override
    public void registerPassengerOnBoard(Passenger passenger) {

        String stationName = passenger.getStartingStation();
        Station station = stationRepository.findByName(stationName).orElseThrow(() -> new StationNotFoundException());
        station.addPassenger(new Passenger(passenger));

        
    }

    @Override
    public void collectTravelCharge(Passenger passenger, int travelCharge) {
        String stationName = passenger.getStartingStation();
        Station station = stationRepository.findByName(stationName).orElseThrow(() -> new StationNotFoundException());
        station.addTripCharge(travelCharge);
    }

    @Override
    public void collectServiceCharge(Passenger passenger, int rechargeAmount) {
        String stationName = passenger.getStartingStation();
        Station station = stationRepository.findByName(stationName).orElseThrow(() -> new StationNotFoundException());
        int serviceCharge= calculateServiceFee(rechargeAmount);
        station.addServiceCharge(serviceCharge);
    }

    private int calculateServiceFee(int rechargeAmount) {
        final double serviceRate = 0.02;
        int serviceCharge = (int)(serviceRate* rechargeAmount);
        return serviceCharge;
    }


    @Override
    public List<Station> getAllStations() {
        List<Station> stations = stationRepository.findAll();
    
        // Sort stations using a custom comparator
        Collections.sort(stations, new Comparator<Station>() {
            @Override
            public int compare(Station s1, Station s2) {
                // Compare station names in descending order
                return s2.getStationName().compareTo(s1.getStationName());
            }
        });
        
        return stations;
    }

    @Override
    public int getTravelCharge(Passenger passenger) {
        String passengerType = passenger.getPassengerType().toString();
        int travelCharge = TravelCharge.valueOf(passengerType).getCharge();

        int tripType = passenger.getTripType();
        TripType journeyType = getTripType(tripType);

        if(journeyType.equals(TripType.SINGLE))
            return travelCharge;
        else
        {
            int chargeForReturn = calculateDiscountedCharge(travelCharge);
            int discount = travelCharge - chargeForReturn;
            collectDiscount(passenger, discount);
            return chargeForReturn;
        }
    }

    private TripType getTripType(int tripType) {
        switch(tripType)
        {
            case 0: return TripType.SINGLE;
            default: return TripType.RETURN;
        }
    }

    private int calculateDiscountedCharge(int travelCharge) {
        final double DISCOUNT_RATE = 0.5;
        return (int)(DISCOUNT_RATE * travelCharge);
    }

    private void collectDiscount(Passenger passenger, int discount) {
        String stationName = passenger.getStartingStation();
        Station station = stationRepository.findByName(stationName).orElseThrow(() -> new StationNotFoundException());
        station.addDiscount(discount);
    }

    @Override
    public CollectionSummary getCollectionSummary(Station station) {
        int total = station.getTotalCharge();
        int discount = station.getDiscount();
        CollectionSummary collectionSummary = new CollectionSummary(station.getStationName(), total, discount);
        return collectionSummary;
    }

    @Override
    public PassengerSummary getPassengerSummary(Station station) {
        List<Passenger> passengers = station.getPassengers();
        Map<PassengerType, Integer> passengerTypeCountMap = getCountPassengerType(passengers);
        List<PassengerTypeSummary> passengerTypes = getPassengerSummaryHelper(passengerTypeCountMap);
        PassengerSummary passengerSummary = new PassengerSummary(passengerTypes);
        return passengerSummary;
    }
    
    private List<PassengerTypeSummary> getPassengerSummaryHelper(Map<PassengerType, Integer> passengerTypeCountMap) {
        List<PassengerTypeSummary> passengerTypeCounts = passengerTypeCountMap.entrySet().stream().map(entry -> new PassengerTypeSummary(entry.getKey(), entry.getValue())).sorted().collect(Collectors.toList());
        return passengerTypeCounts;
    }

    private Map<PassengerType, Integer> getCountPassengerType(List<Passenger> passengers) {
        Map<PassengerType, Integer> passengerTypeCountMap = new HashMap<>();
        final int count = 0;

        for(Passenger passenger : passengers)
        {
            PassengerType passengerType = passenger.getPassengerType();
            int passengerCount = passengerTypeCountMap.getOrDefault(passengerType, count);
            passengerCount++;
            passengerTypeCountMap.put(passengerType, passengerCount);
        }
        return passengerTypeCountMap;
    }
    
}
