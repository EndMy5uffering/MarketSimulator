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
	
	public boolean withdraw(Commodity c) {
		if(this.commoditiys.remove(c)) {
			return true;
		}
		return false;
	}
	
	public boolean withdraw(double amount) {
		if(amount < 0)
			return false;
		if(this.balance - amount > this.balance)
			return false;
		this.balance -= amount;
		return true;
	}
	
	public boolean deposit(Commodity c) {
		if(c == null)
			return false;
		this.commoditiys.add(c);
		return true;
	}
	
	public boolean deposit(double amount) {
		if(amount < 0)
			return false;
		if(this.balance + amount < this.balance)
			return false;
		this.balance += amount;
		return true;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Commodity> getCommodities() {
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
