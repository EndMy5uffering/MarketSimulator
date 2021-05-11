package com.market.eco;

public class Currency {

	private double worth = 1.0;
	private String displayName = "Euro";


	public Currency(double worth, String displayName) {
		this.worth = worth;
		this.displayName = displayName;
	}

	public double getWorth() {
		return worth;
	}

	public String getDisplayName() {
		return displayName;
	}
	
}
