package com.n26.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.n26.dto.StatisticsBuilder;
import com.n26.dto.StatisticsResponse;
import com.n26.dto.Transaction;
import com.n26.repository.TransactionRepository;
import com.n26.repository.TransactionRepositoryImpl;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {    
    private TransactionRepository repository = new TransactionRepositoryImpl();
    private Lock lock = new ReentrantLock();
    
    public void insert (Transaction transaction){
        lock.lock();
        
        try {
            repository.insert(transaction);
        } finally{
            lock.unlock();
        }
    }
    
    public boolean isExpired (Transaction transaction){        
        return Duration
            .between(transaction.getTimestamp(), Instant.now())
            .compareTo(Duration.ofMinutes(1))
        > 0;
    }
    
    public void remove (Transaction transaction){
        lock.lock();
        
        try {
            repository.remove(transaction);
        } finally{
            lock.unlock();
        }
    }
    
    public void removeAll (){
        lock.lock();
        
        try {
            repository.removeAll();
        } finally{
            lock.unlock();
        }
    }
    
    public StatisticsResponse getStatistics (){
        StatisticsBuilder statisticsBuilder = new StatisticsBuilder();
        
        repository.getAll().forEach(transaction -> {
            BigDecimal amount = transaction.getAmount();
            
            statisticsBuilder.count();
            statisticsBuilder.sum(amount);
            statisticsBuilder.avg();
            statisticsBuilder.min(amount);
            statisticsBuilder.max(amount);
        });
        
        return statisticsBuilder.roundHalfUp().build();
    }
    
    public List<Transaction> findAll (){
        return repository.getAll();
    }
    
}
