package com.example.myflightplanner.controllers;

import com.example.myflightplanner.model.Flight;
import com.example.myflightplanner.api.AddFlightRequest;
import com.example.myflightplanner.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/admin-api")
public class FlightAdminController {

    private final FlightService flightService;

    public FlightAdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PutMapping(value = "/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public Flight addFlight(@Valid @RequestBody AddFlightRequest flightRequest) {
        Flight newFlight = flightRequest.flightObject(flightService.getNewId());
        if (flightRequest.isValidRequest(flightRequest)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        flightService.addFlight(newFlight);
        return newFlight;
    }

    @GetMapping(value = "/flights/{id}")
    public Flight getFlight(@PathVariable Long id) {
        return flightService.getFlight(id);
    }

    @DeleteMapping(value = "/flights/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}