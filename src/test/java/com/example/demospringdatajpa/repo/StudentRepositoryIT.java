package com.example.demospringdatajpa.repo;

import com.example.demospringdatajpa.domain.Address;
import com.example.demospringdatajpa.domain.City;
import com.example.demospringdatajpa.domain.Student;
import com.example.demospringdatajpa.domain.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TestTransaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class StudentRepositoryIT extends AbstractDataIT {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Rollback(value = false)
    @Test
    public void should_fetch_student() {
        // GIVEN
        Student john = new Student().setName("John Wick");
        Student john2 = new Student().setName("John Wick2");
        City newyork = new City().setName("Newyork City");
        entityManager.persist(newyork);
        Address address = new Address().setCity(newyork)
                .setLat(12.3)
                .setLon(13.4)
                .setPincode("121007");

        john.setAddress(address);
        john2.setAddress(address);
        Student student1 = studentRepository.saveAndFlush(john);
        Student student2 = studentRepository.saveAndFlush(john2);
        address.setStudent(List.of(john, john2));
        Subject maths = new Subject().setName("maths").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject english = new Subject().setName("english").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject hindi = new Subject().setName("hindi").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject geology = new Subject().setName("geology").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject physics = new Subject().setName("physics").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject science = new Subject().setName("science").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        john.setSubjects(List.of(maths, english, hindi, geology, physics, science));
        john2.setSubjects(List.of(maths, english, hindi, geology, physics, science));

        TestTransaction.end();

        // WHEN
        TestTransaction.start();
        List<Student> students = studentRepository.findAll();
        Student student = studentRepository.findById(student1.getUuid()).get();
        System.out.println(student);
        System.out.println(student.getAddress());
        Address address1 = student.getAddress();
        System.out.println(address1.getLat());


        // THEN
        // check console
    }

    @Test
    @Rollback(value = false)
    public void fetch_children_in_eager_mode_should_trigger_extra_call_for_parent(){
        // GIVEN
        UUID uuid = UUID.randomUUID();
        Student john = new Student().setName("John Wick").setUuid(uuid);
        City newyork = new City().setName("Newyork City");
        Address address = new Address().setCity(newyork)
                .setLat(12.3)
                .setLon(13.4)
                .setPincode("121007");
        john.setAddress(address);
        address.setStudent(List.of(john));
        Subject maths = new Subject().setName("maths").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject english = new Subject().setName("english").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject hindi = new Subject().setName("hindi").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject geology = new Subject().setName("geology").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject physics = new Subject().setName("physics").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        Subject science = new Subject().setName("science").setCourseStartDate(ZonedDateTime.now()).setStudent(john);
        john.setSubjects(List.of(maths, english, hindi, geology, physics, science));
        Student savedStudent = studentRepository.saveAndFlush(john);
        UUID addressUuid = savedStudent.getAddress().getUuid();
        TestTransaction.end();

        // WHEN
        TestTransaction.start();
        Address address1 = entityManager.createQuery("SELECT addr from Address addr where addr.uuid = :uuid", Address.class).setParameter("uuid", addressUuid)
                .getSingleResult();
        System.out.println(address1.getLon());

        // THEN
        // check console for query
    }
}
