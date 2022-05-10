package com.example.myflightplanner.api;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

public class SearchFlightRequest {

    @NotBlank
    private String from;
    @NotBlank
    private String to;

    @JsonFormat (pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    public SearchFlightRequest(String from, String to, LocalDate departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public boolean isValidSearchFlight(SearchFlightRequest searchFlightRequest){
        return searchFlightRequest.getFrom().equalsIgnoreCase(searchFlightRequest.getTo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchFlightRequest)) return false;
        SearchFlightRequest that = (SearchFlightRequest) o;
        return from.equals(that.from) && to.equals(that.to) && departureDate.equals(that.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, departureDate);
    }

}
