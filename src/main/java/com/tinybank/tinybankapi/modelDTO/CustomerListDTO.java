package com.tinybank.tinybankapi.modelDTO;

import java.util.List;

public class CustomerListDTO {

    List<CustomerDTO> customerDTOS;

    public CustomerListDTO(List<CustomerDTO> customerDTOS) {
        this.customerDTOS = customerDTOS;
    }

    public CustomerListDTO() {
    }

    public List<CustomerDTO> getCustomerDTOS() {
        return customerDTOS;
    }

    public void setCustomerDTOS(List<CustomerDTO> customerDTOS) {
        this.customerDTOS = customerDTOS;
    }
}
