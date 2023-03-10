package edu.unsj.fcefn.lcc.optimizacion.api.model.domain;

import java.time.LocalTime;

public class FrameDTO {

    private Integer id;
    private Integer idTransportCompany;
    private Integer idStopDeparture;
    private Integer idStopArrival;
    private Float price;
    private String category;
    private LocalTime departureDateTime;
    private LocalTime arrivalDateTime;

    public Integer getId() { return id; }

    public Integer getIdTransportCompany() { return idTransportCompany; }

    public Integer getIdStopDeparture() { return idStopDeparture; }

    public Integer getIdStopArrival() { return idStopArrival; }

    public Float getPrice() { return price; }

    public String getCategory() { return category; }

    public LocalTime getArrivalDateTime() { return arrivalDateTime; }

    public LocalTime getDepartureDateTime() { return departureDateTime; }

    public void setId(Integer id) { this.id = id; }

    public void setIdTransportCompany(Integer idTransportCompany) { this.idTransportCompany = idTransportCompany; }

    public void setIdStopDeparture(Integer idStopDeparture) { this.idStopDeparture = idStopDeparture; }

    public void setIdStopArrival(Integer idStopArrival) { this.idStopArrival = idStopArrival; }

    public void setPrice(Float price) { this.price = price; }

    public void setCategory(String category) { this.category = category; }

    public void setDepartureDateTime(LocalTime departureDateTime) { this.departureDateTime = departureDateTime; }

    public void setArrivalDateTime(LocalTime arrivalDateTime) { this.arrivalDateTime = arrivalDateTime; }

}
