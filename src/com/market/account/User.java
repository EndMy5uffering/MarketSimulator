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
        List<Account> tempAccounts = new ArrayList<>(this.accounts);
        for(Account a: tempAccounts) {
            temp = temp && removeFromAccount(a);
        }
        return temp;
    }

    public boolean addToAccount(Account account) {
        if(account == null)
            return false;
        this.accounts.add(account);
        account.addUserToAccount(this);
        return true;
    }

    public boolean removeFromAccount(Account account) {
        if(account == null)
            return false;
        this.accounts.remove(account);
        account.removeUserFromAccount(this);
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
