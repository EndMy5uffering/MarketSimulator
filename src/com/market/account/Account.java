package com.market.account;

import java.util.ArrayList;
import java.util.List;

import com.market.eco.Commodity;
import com.market.eco.Currency;

public class Account {

	private List<User> users = new ArrayList<>();
	private List<Commodity> commoditiys = new ArrayList<>();
	private Currency currency = null;
	private double balance = 0.0;
	
	private long lifeTime = 0;
	
	public Account() {
		
	}
	
	public boolean addUserToAccount(User user) {
		if(user == null) return false;
		return users.add(user);
	}
	
	public boolean removeUserFromAccount(User user) {
		if(user == null) return false;
		return users.remove(user);
	}
	
	public Commodity withdrawCommodity() {
		return null;
	}
	
	public double withdraw(double ammount) {
		return 0.0;
	}
	
	public boolean deposit(Commodity c) {
		return false;
	}
	
	public boolean deposit(Currency c) {
		return false;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Commodity> getCommoditiys() {
		return commoditiys;
	}

	public Currency getCurrency() {
		return currency;
	}

	public double getBalance() {
		return balance;
	}

	public long getLifeTime() {
		return lifeTime;
	}
	
}
