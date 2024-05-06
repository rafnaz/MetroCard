package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Station;

public class StationRepositoryImpl implements StationRepository{
    private final Map<String, Station> stationMap ;
    private int autoIncrement = 0;

    public StationRepositoryImpl(Map<String, Station> stationMap) {
        this.stationMap = stationMap;
        autoIncrement = stationMap.size();
    }

    public StationRepositoryImpl() {
        this.stationMap = new HashMap<>();
    }

    @Override
    public Station save(Station entity) {
        if(entity.getId() == null) {
            autoIncrement++;
            Station station = new Station(Integer.toString(autoIncrement), entity.getStationName());
            stationMap.put(station.getId(), station);
            return station;
        }
        stationMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Station> findByName(String stationName) {
        Optional<Station> station = stationMap.values().stream().filter(s -> s.getStationName().equals(stationName)).findFirst();
        return station;
    }

    @Override
    public List<Station> findAll() {
        List<Station> stations = stationMap.values().stream().collect(Collectors.toList());
        return stations;
    }
    
}
