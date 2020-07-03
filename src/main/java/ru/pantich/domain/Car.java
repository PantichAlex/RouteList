package ru.pantich.domain;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_car")
    private long id;

    @Column(name = "name_car", unique = true)
    private String name;

    @Column(name="number_car", unique = true)
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
