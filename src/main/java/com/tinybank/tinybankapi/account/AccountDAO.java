package com.tinybank.tinybankapi.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinybank.tinybankapi.customer.CustomerDAO;

import javax.persistence.*;


@Entity
@Table(name = "accounts")
public class AccountDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties
    private CustomerDAO customerDAO;

    @Column(name = "display_name")
    private String displayName;

    public AccountDAO() {
    }

    public AccountDAO(CustomerDAO customerDAO, String displayName) {

        this.customerDAO = customerDAO;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
