package com.mss.src.iptt.src.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket extends ValueObject {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Carrier carrier;
    private long stops;
    private BigDecimal price;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public long getStops() {
        return stops;
    }

    public void setStops(long stops) {
        this.stops = stops;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Duration getFlightTime() {
        return Duration.between(departureDate, arrivalDate);
    }
}
