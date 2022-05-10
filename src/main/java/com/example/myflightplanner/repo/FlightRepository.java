package com.example.myflightplanner.repo;

import com.example.myflightplanner.api.AddFlightRequest;
import com.example.myflightplanner.api.SearchFlightRequest;
import com.example.myflightplanner.model.Airport;
import com.example.myflightplanner.model.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;


@Repository
public class FlightRepository {
    private Long counterForId = 0L;
    private HashSet<Flight> flightList;
    private HashSet<Airport> airportList;

    public FlightRepository(HashSet<Flight> flightList, HashSet<Airport> airportList) {
        this.flightList = flightList;
        this.airportList = airportList;
    }

    public void getNewId() {
        this.counterForId++;
    }

    public synchronized void addFlight(Flight flight) {
        if (flightList.stream()
                .anyMatch(flight::checkFlightEquality)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (flight.checkInvalidDates(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        flightList.add(flight);
    }

    public synchronized List<Airport> searchAirport(String search) {
        HashSet<Airport> newAirportList = new HashSet<>();
        newAirportList.addAll(flightList.stream()
                .map(Flight::getFrom)
                .toList());

        newAirportList.addAll(flightList.stream()
                .map(Flight::getTo)
                .toList());

        return newAirportList.stream()
                .filter(airport -> airport.getAirport().toLowerCase().contains(search)
                        || airport.getCity().toLowerCase().contains(search)
                        || airport.getCountry().toLowerCase().contains(search))
                .toList();
    }

    public synchronized Long getCounterForId() {
        return counterForId;
    }

    public synchronized void deleteFlight(Long id) {
        this.flightList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .ifPresent(flight -> flightList.remove(flight));
    }

    public Flight getFlight(Long id) {
        return this.flightList.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void clear() {
        flightList.clear();
    }

    public synchronized List<Flight> searchFlight(SearchFlightRequest searchFlightRequest) {
        if (searchFlightRequest.isValidSearchFlight(searchFlightRequest)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return flightList.stream()
                .filter(flight -> flight.getFrom().getAirport().equals(searchFlightRequest.getFrom())
                        && flight.getTo().getAirport().equals(searchFlightRequest.getTo())
                        && flight.getDepartureTime().toLocalDate().equals(searchFlightRequest.getDepartureDate()))
                .toList();
    }

    public synchronized Flight findFlightById(Long id) {
        return flightList.stream()
                .filter(flight -> flight.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
