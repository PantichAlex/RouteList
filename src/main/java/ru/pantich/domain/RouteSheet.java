package ru.pantich.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "route_sheet")
public class RouteSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_route_sheet")
    private long id;



    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_driver", referencedColumnName = "id_driver")
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car", referencedColumnName = "id_car")
    private Car car;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_specialist", referencedColumnName = "id_specialist")
    private Specialist specialist;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeSheet")
    private List<Waybill> waybill=new ArrayList<Waybill>();



    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd.MM.YYYY HH:mm")
    @Column(name="date")
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Waybill> getWaybill() {
        return waybill;
    }

    public void setWaybill(List<Waybill> waybill) {
        this.waybill = waybill;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
