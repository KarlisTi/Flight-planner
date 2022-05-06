package com.example.myflightplanner.service;

import com.example.myflightplanner.api.SearchFlightRequest;
import com.example.myflightplanner.model.Airport;
import com.example.myflightplanner.repo.FlightRepository;
import com.example.myflightplanner.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void addFlight(Flight flight) {
        flightRepository.addFlight(flight);
    }

    public Flight getFlight(Long id) {
        return flightRepository.getFlight(id);
    }

    public List<Airport> searchAirport(String search) {
        return flightRepository.searchAirport(search.trim().toLowerCase());
    }

    public List<Flight> searchFlight(SearchFlightRequest searchFlightRequest) {
        return flightRepository.searchFlight(searchFlightRequest);
    }

    public Flight findFlightById(Long id) {
        return flightRepository.findFlightById(id);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteFlight(id);
    }

    public void clearFlight() {
        flightRepository.clear();
    }

    public Long getNewId() {
        flightRepository.getNewId();
        return flightRepository.getCounterForId();
    }


    @Override
    public String toString() {
        return "FlightService{" +
                "flightRepository=" + flightRepository +
                '}';
    }
}
