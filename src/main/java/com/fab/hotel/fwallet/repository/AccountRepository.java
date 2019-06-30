package com.fab.hotel.fwallet.repository;

import com.fab.hotel.fwallet.model.Account;
import com.fab.hotel.fwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByUser(User user);
}
