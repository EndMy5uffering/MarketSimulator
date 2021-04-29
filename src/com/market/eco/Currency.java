package com.market.eco;

public class Currency {

	private Currencys currencys;
	private double ammount = 0.0;
	
	public Currency(double ammount, Currencys currency) {
		this.currencys = currency;
		this.ammount = ammount;
	}

	public Currencys getCurrencys() {
		return currencys;
	}

	public double getAmmount() {
		return ammount;
	}
	
}
