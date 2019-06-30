package com.fab.hotel.fwallet.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "phoneNum", nullable = false, unique = true)
    private String phoneNum;
    @Column(name = "emailID", nullable = false, unique = true)
    private String emailID;
    @Column(name = "password")
    private String password;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "gender")
    private String gender;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "accountNumber", nullable = false, unique = true)
//    private Account account;
    public User() {
    }

//    public Account getAccount() {
//        return this.account;
//    }
//
//    public void setAccount(final Account account) {
//        this.account = account;
//    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(final String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmailID() {
        return this.emailID;
    }

    public void setEmailID(final String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(final Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }
}
