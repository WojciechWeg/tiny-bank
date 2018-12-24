package com.tinybank.tinybankapi.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinybank.tinybankapi.account.AccountDAO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "customers")
public class CustomerDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customerDAO")
    @JsonIgnoreProperties(value = {"customerDAO"})
    private List<AccountDAO> accountDAOS;

    public CustomerDAO() {
    }

    public CustomerDAO(String name, String surname, Date birthDate, String address, List<AccountDAO> accountDAOS) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
        this.accountDAOS = new ArrayList<AccountDAO>(accountDAOS);
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

    public List<AccountDAO> getAccountDAOS() {
        return new ArrayList<AccountDAO>(accountDAOS);
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

    public void setAccountDAOS(List<AccountDAO> accountDAOS) {
        this.accountDAOS = accountDAOS;
    }

    public void addAccount(AccountDAO accountDAO) {
        this.accountDAOS.add(accountDAO);
    }
}
