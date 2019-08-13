package com.n26.repository;

import java.util.ArrayList;
import java.util.List;

import com.n26.dto.Transaction;

/*
 * Repository pattern was chosen to abstracts the data storage
 * to allow overwriting of datastore without changing business code
 */
public class TransactionRepositoryImpl implements TransactionRepository{
    private List<Transaction> transactions;
    
    public TransactionRepositoryImpl (){
        transactions = new ArrayList<Transaction>();
    }
    
    @Override
    public void insert (Transaction transaction){
        transactions.add(transaction);
    }
    
    @Override
    public void removeAll (){
        transactions.clear();
    }
    
    @Override
    public List<Transaction> getAll (){
        return transactions;
    }
    
    @Override
    public void remove (Transaction transaction){
        transactions.remove(transaction);
    }
}
