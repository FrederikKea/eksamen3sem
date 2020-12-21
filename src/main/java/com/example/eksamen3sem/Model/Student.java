package com.example.eksamen3sem.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "students_supervisor",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "supervisor_id"))
    private Set<Supervisor> supervisorSet = new HashSet<>();

    public Student() {
    }

    public Student(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Supervisor> getSupervisorSet() {
        return supervisorSet;
    }

    public void setSupervisorSet(Set<Supervisor> supervisorSet) {
        this.supervisorSet = supervisorSet;
    }
}