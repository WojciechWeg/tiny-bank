package com.tinybank.tinybankapi.modelDTO;

public class AccountDTO {


    private  String displayName;

    public AccountDTO() {
    }

    public AccountDTO( String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
