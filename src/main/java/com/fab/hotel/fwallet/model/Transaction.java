package com.fab.hotel.fwallet.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "TransactionTime")
    private Date transactionTime = new Date();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FromUserId")
    private User fromUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toUserId")
    private User toUser;

    @Column(name = "TransactionType", nullable = false)
    private TransactionType transactionType;

    public long getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(final long transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public Date getTransactionTime() {
        return this.transactionTime;
    }

    public void setTransactionTime(final Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public User getFromUser() {
        return this.fromUser;
    }

    public void setFromUser(final User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return this.toUser;
    }

    public void setToUser(final User toUser) {
        this.toUser = toUser;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
