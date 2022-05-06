package com.example.myflightplanner.controllers;

import com.example.myflightplanner.api.PageResult;
import com.example.myflightplanner.api.SearchFlightRequest;
import com.example.myflightplanner.model.Airport;
import com.example.myflightplanner.model.Flight;
import com.example.myflightplanner.service.FlightService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class FlightCustomerController {

    public final FlightService flightService;

    public FlightCustomerController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(value = "/airports")
    public List<Airport> searchAirports(@RequestParam String search) {
        return flightService.searchAirport(search);
    }

    @PostMapping(value = "/flights/search")
    public PageResult<Flight> searchFlight(@Valid @RequestBody SearchFlightRequest searchFlightRequest) {
        List<Flight> newList = flightService.searchFlight(searchFlightRequest);
        return new PageResult<>(0, newList.size(), newList);
    }

    @GetMapping(value = "/flights/{id}")
    public Flight findFlightById(@PathVariable("id") Long id) {
        return flightService.findFlightById(id);
    }
}
