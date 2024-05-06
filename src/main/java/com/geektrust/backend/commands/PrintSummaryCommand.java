package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.dtos.CollectionSummary;
import com.geektrust.backend.dtos.PassengerSummary;
import com.geektrust.backend.dtos.PassengerTypeSummary;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.services.StationService;

public class PrintSummaryCommand implements ICommand{
    private final StationService stationService;

    public PrintSummaryCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public void execute(List<String> tokens) {
        List<Station> stations = stationService.getAllStations();

        for(Station station : stations)
        {
            printCollectionSummary(station);
            printPassengerSummary(station);
        }
    }

    private void printCollectionSummary(Station station) {
        CollectionSummary collectionSummary = stationService.getCollectionSummary(station);
        System.out.println("TOTAL_COLLECTION " + collectionSummary.getStationName() + " " + collectionSummary.getTotalCharge() + " " + collectionSummary.getDiscount());
    }

    private void printPassengerSummary(Station station) {
        PassengerSummary passengerSummary = stationService.getPassengerSummary(station);
        List<PassengerTypeSummary> passengerTypeCounts = passengerSummary.getPassengerTypeSummary();

        System.out.println("PASSENGER_TYPE_SUMMARY");

        for(PassengerTypeSummary passengerTypeCount : passengerTypeCounts)
            System.out.println(passengerTypeCount.getPassengerType() + " " + passengerTypeCount.getPassengerCount());
    }
    
}
