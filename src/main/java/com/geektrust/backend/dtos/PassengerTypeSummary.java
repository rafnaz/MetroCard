package com.geektrust.backend.dtos;

import com.geektrust.backend.entities.PassengerType;

public class PassengerTypeSummary implements Comparable<PassengerTypeSummary>{
    private final PassengerType passengerType;
    private final int passengerCount;

    public PassengerTypeSummary(PassengerType passengerType, int count) {
        this.passengerType = passengerType;
        this.passengerCount = count;
    }

    public PassengerType getPassengerType() {
        return this.passengerType;
    }
    public int getPassengerCount() {
        return this.passengerCount;
    }

    @Override
    public int compareTo(PassengerTypeSummary other) {
        int countDifference = other.passengerCount- this.passengerCount;
        if (countDifference != 0) {
            return countDifference;
        }
        return this.passengerType.compareTo(other.passengerType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        PassengerTypeSummary other = (PassengerTypeSummary) obj;
        if((this.passengerType.equals(other.passengerType)) && (this.passengerCount == other.passengerCount))
            return true;
        
        return false;
    }

    
}
