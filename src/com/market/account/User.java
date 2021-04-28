package com.market.account;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String uuid;
    private String userName;
    private List<Account> accounts= new ArrayList<>();

    public User(String uuid, String userName) {
        this.uuid = uuid;
        this.userName = userName;
    }

    public boolean deleteUser() {
        boolean temp = true;
        for(Account a: this.accounts) {
            temp = temp && removeFromAccount(a);
        }
        return temp;
    }

    public boolean addToAccount(Account account) {
        if(account == null)
            return false;
        //TODO: Call function in Account obj to add user
        this.accounts.add(account);
        return true;
    }

    public boolean removeFromAccount(Account account) {
        if(account == null)
            return false;
        //TODO: Call function in Account obj to remove user
        this.accounts.remove(account);
        return true;


    }

    public String getUuid() {
        return uuid;
    }

    public String getUserName() {
        return userName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
