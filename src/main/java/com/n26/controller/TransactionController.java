package com.n26.controller;

import javax.validation.Valid;

import com.n26.dto.StatisticsResponse;
import com.n26.dto.Transaction;
import com.n26.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    @PostMapping("/transactions")
    public ResponseEntity addTransaction (@Valid @RequestBody Transaction transaction){
        if (transactionService.isExpired(transaction)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            transactionService.insert(transaction);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }
    
    @GetMapping("/statistics")
    public StatisticsResponse getStatistics(){
        return transactionService.getStatistics();
    }
    
    @DeleteMapping("/transactions")
    public ResponseEntity deleteAllTransactions(){
        transactionService.removeAll();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
