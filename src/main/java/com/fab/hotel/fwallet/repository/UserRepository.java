package com.fab.hotel.fwallet.repository;

import com.fab.hotel.fwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByPhoneNum(String phoneNum);
}
