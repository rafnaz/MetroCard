package com.geektrust.backend.dtos;

public class CollectionSummary {
    private final String stationName;
    private final int totalCharge;
    private final int discount;

    public CollectionSummary(String stationName, int totalCharge, int discount) {
        this.stationName = stationName;
        this.totalCharge = totalCharge;
        this.discount = discount;
    }

    public String getStationName() {
        return this.stationName;
    }

    public int getTotalCharge() {
        return this.totalCharge;
    }

    public int getDiscount() {
        return this.discount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        CollectionSummary other = (CollectionSummary) obj;
        if ((this.stationName.equals(other.stationName)) && (this.totalCharge == other.totalCharge) && (this.discount == other.discount))
            return true;
    
        return false;
    }
}
