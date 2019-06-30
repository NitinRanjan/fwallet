package com.fab.hotel.fwallet.model;

import java.util.Date;

public class TransactionHistory implements Comparable<Date>{
    String type;
    Date date;
    Double amount;
    String message;

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public int compareTo(Date o) {
        return this.getDate().compareTo(o);
    }
}
