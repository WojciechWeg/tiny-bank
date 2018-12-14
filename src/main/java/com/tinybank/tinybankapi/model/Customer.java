package com.tinybank.tinybankapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "name")
    private  String name;

    @Column(name= "surname")
    private  String surname;

    @Column(name = "birth_date")
    private  Date birthDate;

    @Column(name = "address")
    private  String address;

    @OneToMany(mappedBy = "customer",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private  List<Account> accounts;

    public Customer() {
    }

    public Customer(String name, String surname, Date birthDate, String address, List<Account> accounts) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
        this.accounts = new ArrayList<Account>(accounts);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return new ArrayList<Account>(accounts);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
