package com.tinybank.tinybankapi.model;

import javax.persistence.*;


@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private  Customer customer;

    @Column(name="display_name")
    private  String displayName;

    public Account() {
    }

    public Account(Customer customer, String displayName) {

        this.customer = customer;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
