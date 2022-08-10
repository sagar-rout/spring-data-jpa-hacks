package com.example.demospringdatajpa.domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects;

    public Address getAddress() {
        return address;
    }

    public Student setAddress(Address address) {
        this.address = address;
        return this;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Student setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Student setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }
}
