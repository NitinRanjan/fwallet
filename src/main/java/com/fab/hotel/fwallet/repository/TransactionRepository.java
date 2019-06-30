package com.fab.hotel.fwallet.repository;

import com.fab.hotel.fwallet.model.Account;
import com.fab.hotel.fwallet.model.Transaction;
import com.fab.hotel.fwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findAllByFromUser(User user);
    public List<Transaction> findAllByToUser(User user);
}
