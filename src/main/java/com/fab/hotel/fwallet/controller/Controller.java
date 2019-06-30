package com.fab.hotel.fwallet.controller;


import com.fab.hotel.fwallet.model.*;
import com.fab.hotel.fwallet.repository.AccountRepository;
import com.fab.hotel.fwallet.repository.TransactionRepository;
import com.fab.hotel.fwallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/users")
    public List<User> getAlluser() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public ResponseEntity<?> createuser(@Valid @RequestBody User user) {
        User newuser = userRepository.save(user);
        Account account = new Account();
        account.setBalance(Double.valueOf(0));
        account.setOpeningDate(new Date());
        if(newuser==null)
        {
            account.setUser(newuser);
            accountRepository.save(account);
            return ResponseEntity.notFound().build();
        }
        account.setUser(newuser);
        accountRepository.save(account);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{phoneNum}")
    public User getuserByPhoneNumber(@PathVariable(value = "phoneNum") String phoneNum) throws ResourceNotFoundException{
        return userRepository.findByPhoneNum(phoneNum).orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNum, Long.valueOf(0)));
    }

    @PutMapping("/user/{phoneNum}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "phoneNum") String phoneNum,
                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException{
        User user = userRepository.findByPhoneNum(phoneNum)
                .orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNum, Long.valueOf(0)));
        final User updateduser = userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{phoneNum}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "phoneNum") String phoneNum) throws ResourceNotFoundException{
        User user = userRepository.findByPhoneNum(phoneNum)
                .orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNum, Long.valueOf(0)));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/addmoney/{phoneNum}")
    public ResponseEntity<?> addMoney(@PathVariable(value = "phoneNum") String phoneNumber,
                            @Valid @RequestBody AddMoney addMoney) throws ResourceNotFoundException{
        Double amount = Double.valueOf(addMoney.getAmount());
        User user = userRepository.findByPhoneNum(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNumber, Long.valueOf(0)));
        Account ac = accountRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNumber, Long.valueOf(0)));
        ac.setBalance(ac.getBalance()+amount);
        Account account =  accountRepository.save(ac);
        Transaction transaction = new Transaction();
        transaction.setAmount(addMoney.getAmount());
        transaction.setFromUser(user);
        transaction.setToUser(user);
        transaction.setTransactionType(TransactionType.SELF);
        transactionRepository.save(transaction);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/pay/{phoneNum}")
    public ResponseEntity<?> pay(@PathVariable(value = "phoneNum") String phoneNumber,
                                 @Valid @RequestBody PayMoney payMoney) throws Exception {
        User user = userRepository.findByPhoneNum(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNumber, Long.valueOf(0)));
        Account ac = accountRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNumber, Long.valueOf(0)));
        double amount = payMoney.getAmount();
        String phoneNumberto = payMoney.getPhoneNum();
        if(ac.getBalance()<amount)
            throw new Exception();
        else{
            ac.setBalance(ac.getBalance()-amount);
            User userTo = userRepository.findByPhoneNum(phoneNumberto)
                    .orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNumberto, Long.valueOf(0)));
            Account acto = accountRepository.findByUser(userTo).orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNumberto, Long.valueOf(0)));
            acto.setBalance(acto.getBalance()+amount);
            accountRepository.save(acto);
            accountRepository.save(ac);

            Transaction transaction = new Transaction();
            transaction.setAmount(payMoney.getAmount());
            transaction.setFromUser(user);
            transaction.setToUser(userTo);
            transaction.setTransactionType(TransactionType.TRANSFER);
            transactionRepository.save(transaction);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/transaction/{phoneNum}")
    public Set<TransactionHistory> getTransaction(@PathVariable(value = "phoneNum") String phoneNumber) throws ResourceNotFoundException {
        Set<TransactionHistory> transactionHistories = new TreeSet<TransactionHistory>();
        User user = userRepository.findByPhoneNum(phoneNumber).orElseThrow(() -> new ResourceNotFoundException("user", "phone Number: "+phoneNumber, Long.valueOf(0)));
        List<Transaction>  receivedTransaction =  transactionRepository.findAllByFromUser(user);
        List<Transaction> sentTransaction =  transactionRepository.findAllByToUser(user);
        receivedTransaction.stream().forEach(transaction -> {
            addTransactionHistory(transactionHistories, transaction, "Recieved from: "+transaction.getToUser(), "CR");
        });
        sentTransaction.stream().forEach(transaction -> {
            addTransactionHistory(transactionHistories, transaction, "Sent to: "+transaction.getFromUser(), "DB");
        });
        return transactionHistories;
    }

    private void addTransactionHistory(Set<TransactionHistory> transactionHistories, Transaction transaction, String message, String type) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setDate(transaction.getTransactionTime());
        transactionHistory.setAmount(transaction.getAmount());
        transactionHistory.setMessage(message);
        transactionHistory.setType(type);
        transactionHistories.add(transactionHistory);
    }
}
