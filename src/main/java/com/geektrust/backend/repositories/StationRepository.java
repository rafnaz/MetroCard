package com.geektrust.backend.repositories;

import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.Station;

public interface StationRepository {
    public Station save(Station entity);
    Optional<Station> findByName(String stationName);
    public List<Station> findAll();
}
