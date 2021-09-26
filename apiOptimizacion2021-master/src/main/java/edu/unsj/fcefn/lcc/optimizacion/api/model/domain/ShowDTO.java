package edu.unsj.fcefn.lcc.optimizacion.api.model.domain;

import java.time.LocalTime;

public class ShowDTO {
    private Integer id;
    private String nameTransportCompany;
    private String nameStopDeparture;
    private String nameStopArrival;
    private Float price;
    private LocalTime departureDateTime;
    private LocalTime arrivalDateTime;

    public Integer getId() { return id; }

    public String getNameTransportCompany() { return nameTransportCompany; }

    public String getNameStopDeparture() { return nameStopDeparture; }

    public String getNameStopArrival() { return nameStopArrival; }

    public Float getPrice() { return price; }

    public LocalTime getArrivalDateTime() { return arrivalDateTime; }

    public LocalTime getDepartureDateTime() { return departureDateTime; }

    public void setId(Integer id) { this.id = id; }

    public void setNameTransportCompany(String nameTransportCompany) { this.nameTransportCompany = nameTransportCompany; }

    public void setNameStopDeparture(String nameStopDeparture) { this.nameStopDeparture = nameStopDeparture; }

    public void setNameStopArrival(String nameStopArrival) { this.nameStopArrival = nameStopArrival; }

    public void setPrice(Float price) { this.price = price; }

    public void setDepartureDateTime(LocalTime departureDateTime) { this.departureDateTime = departureDateTime; }

    public void setArrivalDateTime(LocalTime arrivalDateTime) { this.arrivalDateTime = arrivalDateTime; }
}
