package com.market.eco;

import java.util.UUID;

public class Commodity {

    private String displayName;
    private double value;
    private String type;
    private UUID id;
    private long amount;

    public Commodity(String displayname, double value, UUID id, String type, long amount) {
        this.displayName = displayname;
        this.value = value;
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

}
