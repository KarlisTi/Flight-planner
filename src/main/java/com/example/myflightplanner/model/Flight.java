package com.example.myflightplanner.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Flight {

    private Long id;
    @Valid
    @NotNull
    private Airport from;
    @Valid
    @NotNull
    private Airport to;
    @NotBlank
    private String carrier;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Flight(Long id, Airport from, Airport to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public boolean checkFlightEquality(Flight flight) {
        return this.from.equals(flight.from) &&
                this.to.equals(flight.to) &&
                this.carrier.equals(flight.carrier) &&
                this.departureTime.equals(flight.departureTime) &&
                this.arrivalTime.equals(flight.arrivalTime);
    }

    public boolean checkInvalidDates(Flight flight) {
        return flight.departureTime.equals(flight.arrivalTime) || flight.departureTime.isAfter(flight.arrivalTime);
    }

    @Override
    public String toString() {
        return "Flight{"
                +
                "id=" + id
                +
                ", from=" + from
                +
                ", to=" + to
                +
                ", carrier='" + carrier + '\''
                +
                ", departureTime='" + departureTime + '\''
                +
                ", arrivalTime='" + arrivalTime + '\''
                +
                '}';
    }

}
