package com.geektrust.backend.dtos;

import java.util.List;

public class PassengerSummary {
    private final List<PassengerTypeSummary> passengerTypeSummary;

    public PassengerSummary(List<PassengerTypeSummary> passengerTypeSummary) {
        this.passengerTypeSummary = passengerTypeSummary;
    }

    public List<PassengerTypeSummary> getPassengerTypeSummary() {
        return this.passengerTypeSummary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        PassengerSummary other = (PassengerSummary) obj;
        if (this.passengerTypeSummary.equals(other.passengerTypeSummary))
            return true;

        return false;
    }
}
