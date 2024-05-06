package com.geektrust.backend.entities;

import com.geektrust.backend.exceptions.InvalidStationException;

public class Passenger extends BaseEntity {
    
    private final MetroCard metroCard;
    private final PassengerType passengerType;
    private String startingStation;
    private int tripType;

    //copy constructor
    public Passenger(Passenger passenger){
        this(passenger.id,passenger.metroCard,passenger.passengerType,passenger.startingStation);
    }

    //constructor,id,card,tpassengerType,boarding station
    public Passenger(String id, MetroCard metroCard, PassengerType passengerType, String startingStation){
        this(metroCard, passengerType, startingStation);
        this.id = id;
    }

    public Passenger(MetroCard metroCard, PassengerType passengerType, String startingStation){
        this.metroCard = metroCard;
        this.passengerType = passengerType;
        this.startingStation = startingStation;
        this.tripType = -1; //journey yet to start
    }

    //set station
    public void setStartingStation(String startingStation) {
        validateStation(startingStation);
        this.startingStation = startingStation;
    }

    //getters
    public MetroCard getMetroCard() {
        return this.metroCard;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public String getStartingStation() {
        return startingStation;
    }

    public int getTripType() {
        return tripType;
    }

    private void validateStation(String stationName) {
        if(stationName == null) {
            throw new InvalidStationException();
        }
    }

    //update trip type 
    public void updateTripType() {
        int counter = 1;
        int stationCount = 2;
        this.tripType = (tripType + counter) % stationCount; //0:single direction,1: return trip
    }

    @Override
    public int hashCode() { 
        final int prime = 31;
        int result = 1;
        result = prime * result + ((startingStation == null) ? 0 : startingStation.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((metroCard == null) ? 0 : metroCard.hashCode());
        result = prime * result + ((passengerType == null) ? 0 : passengerType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){

        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()) {
            return false;
        }
    
        Passenger other = (Passenger) obj;
        if((this.metroCard.equals(other.metroCard)) && (this.passengerType.equals(other.passengerType)) && (this.startingStation.equals(other.startingStation)))
            return true;
    
        return false;
    }



}
