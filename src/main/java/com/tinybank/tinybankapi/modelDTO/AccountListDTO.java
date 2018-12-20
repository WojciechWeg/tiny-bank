package com.tinybank.tinybankapi.modelDTO;

import java.util.List;

public class AccountListDTO {

    List<AccountDTO> accountDTO;

    public AccountListDTO() {
    }

    public AccountListDTO(List<AccountDTO> accountDTO) {
        this.accountDTO = accountDTO;
    }

    public List<AccountDTO> getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(List<AccountDTO> accountDTO) {
        this.accountDTO = accountDTO;
    }
}
