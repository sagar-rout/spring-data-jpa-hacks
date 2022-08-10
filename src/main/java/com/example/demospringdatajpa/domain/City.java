package com.example.demospringdatajpa.domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class City {

    @Id
    @GeneratedValue
    private UUID uuid;

    @OneToMany
    private List<Address> addressList;

    @Column(nullable = false)
    private String name;

    public UUID getUuid() {
        return uuid;
    }

    public City setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }
}
