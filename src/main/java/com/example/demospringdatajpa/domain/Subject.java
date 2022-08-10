package com.example.demospringdatajpa.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Subject {

    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private ZonedDateTime courseStartDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    public Student getStudent() {
        return student;
    }

    public Subject setStudent(Student student) {
        this.student = student;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Subject setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public ZonedDateTime getCourseStartDate() {
        return courseStartDate;
    }

    public Subject setCourseStartDate(ZonedDateTime courseStartDate) {
        this.courseStartDate = courseStartDate;
        return this;
    }
}
