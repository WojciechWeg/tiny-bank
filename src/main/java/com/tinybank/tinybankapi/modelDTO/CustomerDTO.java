package com.tinybank.tinybankapi.modelDTO;

import java.util.Date;
import java.util.List;

public class CustomerDTO {

    private String name;

    private String surname;

    private Date birthDate;

    private String address;


    public CustomerDTO() {
    }

    public CustomerDTO(String name, String surname, Date birthDate, String address) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
