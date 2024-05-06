package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.Station;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StationRepositoryImplTest {
    private StationRepository stationRepository;

    @BeforeEach
    public void setUp() {
        Map<String, Station> stationMap = new HashMap<>();
        stationMap.put("1", new Station("1", "CENTRAL"));

        stationRepository = new StationRepositoryImpl(stationMap); 
    }

    @Test
    @DisplayName("save method should create and return a new station")
    public void saveStation() {
        //Arrange
        Station station2 = new Station( "AIRPORT");
        Station expected = new Station("2", "AIRPORT");

        //Act
        Station actual = stationRepository.save(station2);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findByName should return a station for a given station")
    public void findByStation_ShouldReturnStation() {
        //Arrange
        Station expected = new Station("1", "CENTRAL");
       
        //Act
        Station actual = stationRepository.findByName("CENTRAL").get();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findByName should return a empty station if station not found")
    public void findByStation_ShouldReturnEmptyStation() {
        //Arrange
        Optional<Station> expected = Optional.empty();

        //Act
        Optional<Station> actual = stationRepository.findByName("DELHI");

        //Assert
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("findAll should return all stations")
    public void findALLStation() {
        //Arrange
        int expected = 1;

        //Act
        int actual = stationRepository.findAll().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

}
