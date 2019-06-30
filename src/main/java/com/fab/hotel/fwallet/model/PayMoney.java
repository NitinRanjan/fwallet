package com.fab.hotel.fwallet.model;

public class PayMoney {
    private double amount;
    private String message;
    private String phoneNum;

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(final String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
