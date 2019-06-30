package com.fab.hotel.fwallet.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "openingDate")
    private Date openingDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private User user;
    public Long getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(final Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(final Double balance) {
        this.balance = balance;
    }

    public Date getOpeningDate() {
        return this.openingDate;
    }

    public void setOpeningDate(final Date openingDate) {
        this.openingDate = openingDate;
    }
        public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
