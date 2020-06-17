package ru.pantich.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "waybill")
public class Waybill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_waybill")
    private long id;

    @Column(name = "address_delivery_point")
    private String addressDelivery;

    @Column(name="name_delivery_point")
    private String nameDelivery;

    @Column(name="Phone")
    private String phone;

    @ManyToOne
    private RouteSheet routeSheet;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="waybill_product",
            joinColumns = {@JoinColumn(name = "id_waybill")},
            inverseJoinColumns = {@JoinColumn(name="id_product")}

    )
    private List<Product> products=new ArrayList<Product>();

    public RouteSheet getRouteSheet() {
        return routeSheet;
    }

    public void setRouteSheet(RouteSheet routeSheet) {
        this.routeSheet = routeSheet;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressDelivery() {
        return addressDelivery;
    }

    public void setAddressDelivery(String addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    public String getNameDelivery() {
        return nameDelivery;
    }

    public void setNameDelivery(String nameDelivery) {
        this.nameDelivery = nameDelivery;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
