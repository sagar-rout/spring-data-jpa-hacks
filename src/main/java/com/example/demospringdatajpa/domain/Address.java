package com.example.demospringdatajpa.domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    private City city;

    @Column(nullable = false)
    private String pincode;

    @OneToMany
    private List<Student> student;

    @Column
    private double lat;

    @Column
    private double lon;

    public UUID getUuid() {
        return uuid;
    }

    public Address setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public City getCity() {
        return city;
    }

    public Address setCity(City city) {
        this.city = city;
        return this;
    }

    public String getPincode() {
        return pincode;
    }

    public Address setPincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Address setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Address setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public List<Student> getStudent() {
        return student;
    }

    public Address setStudent(List<Student> student) {
        this.student = student;
        return this;
    }
}
