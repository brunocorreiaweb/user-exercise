package com.qumu.data.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", catalog = "qumu", uniqueConstraints = {
        @UniqueConstraint(columnNames = "NAME") })
public class User {

    private Long dob;
    private String name;

    public User() {
    }

    public User(String name, Long dob) {
        this.name = name;
        this.dob = dob;
    }

    @Id
    @Column(name = "NAME", unique = true, nullable = false, length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DOB", nullable = false)
    public Long getDob() {
        return this.dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }
}
