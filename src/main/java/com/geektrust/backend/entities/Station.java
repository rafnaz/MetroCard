package com.geektrust.backend.entities;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.backend.exceptions.InValidAmountException;
import com.geektrust.backend.exceptions.InValidPassengerException;

public class Station extends BaseEntity {

    private final String stationName;
    private List<Passenger> passengers;
    private int tripCharge;
    private int serviceCharge;
    private int discount;
    private static final int minAmount=0;

    public Station(String id, String stationName) {
        this(stationName);
        this.id = id;
    }

    //constructor ,station name
    public Station(String stationName) {
        this.stationName = stationName;
        this.passengers = new ArrayList<>();
        this.tripCharge = 0;
        this.serviceCharge = 0;
        this.discount = 0;
    }

    //getters
    public String getStationName() {
        return this.stationName;
    }
    
    public List<Passenger> getPassengers() {
        return this.passengers;
    }

    public int getTripCharge() {
        return this.tripCharge;
    }

    public int getServiceCharge() {
        return this.serviceCharge;
    }

    public int getDiscount() {
        return this.discount;
    }

    //total charge to travel serviceCharge + tripCharge
    public int getTotalCharge(){
        return this.serviceCharge + this.tripCharge;
    }

    public void validateAmount(int amount){
        if(amount <= minAmount) {
            throw new InValidAmountException("not a valid amount");
        }
    }

    public void validatePassenger( Passenger passenger){
        if(passenger == null) {
            throw new InValidPassengerException("not a valid passenger");
        }
    }

    public void addTripCharge(int charge) {
        validateAmount(charge);
        this.tripCharge += charge;
    }

    public void addServiceCharge(int charge) {
        validateAmount(charge);
        this.serviceCharge += charge;
    }

    public void addDiscount(int discount) {
        validateAmount(discount);
        this.discount += discount;
    }

    public void addPassenger(Passenger passenger) {
        validatePassenger(passenger);
        this.passengers.add(passenger);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((stationName == null) ? 0 : stationName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Station other = (Station) obj;
        if(this.stationName.equals(other.stationName))
            return true;
            
        return false;
    }

}
