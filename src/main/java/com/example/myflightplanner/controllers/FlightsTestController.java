package com.example.myflightplanner.controllers;

import com.example.myflightplanner.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/testing-api")
public class FlightsTestController {

    private final FlightService flightService;

    public FlightsTestController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clear() {
        flightService.clearFlight();
        return new ResponseEntity<>("Data cleared", HttpStatus.OK);
    }


}