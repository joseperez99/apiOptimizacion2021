package edu.unsj.fcefn.lcc.optimizacion.api.model.entities;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "frames")
public class FrameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_transport_company")
    private Integer idTrasportCompany;

    @Column(name = "id_stop_departure")
    private Integer idStopDeparture;

    @Column(name = "id_stop_arrival")
    private Integer idStopArrival;

    @Column(name = "price")
    private Float price;

    @Column(name = "category")
    private String category;

    @Column(name = "departure_datetime")
    private LocalTime departureDateTime;

    @Column(name = "arrival_datetime")
    private LocalTime arrivalDateTime;

    public Integer getId() { return id; }

    public Integer getIdTrasportCompany() { return idTrasportCompany; }

    public Integer getIdStopDeparture() { return idStopDeparture; }

    public Integer getIdStopArrival() { return idStopArrival; }

    public Float getPrice() { return price; }

    public String getCategory() { return category; }

    public LocalTime getArrivalDateTime() { return arrivalDateTime; }

    public LocalTime getDepartureDateTime() { return departureDateTime; }

    public void setId(Integer id) { this.id = id; }

    public void setIdTrasportCompany(Integer idTrasportCompany) { this.idTrasportCompany = idTrasportCompany; }

    public void setIdStopDeparture(Integer idStopDeparture) { this.idStopDeparture = idStopDeparture; }

    public void setIdStopArrival(Integer idStopArrival) { this.idStopArrival = idStopArrival; }

    public void setPrice(Float price) { this.price = price; }

    public void setCategory(String category) { this.category = category; }

    public void setDepartureDateTime(LocalTime departureDateTime) { this.departureDateTime = departureDateTime; }

    public void setArrivalDateTime(LocalTime arrivalDateTime) { this.arrivalDateTime = arrivalDateTime; }

}
