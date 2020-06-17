package ru.pantich.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Waybill> waybills=new ArrayList<Waybill>();


    public List<Waybill> getWaybills() {
        return waybills;
    }

    public void setWaybills(List<Waybill> waybills) {
        this.waybills = waybills;
    }


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


}
