package com.n26.repository;

import java.util.List;

import com.n26.dto.Transaction;

public interface TransactionRepository {
    public void insert(Transaction transaction);
    public void removeAll();
    public void remove (Transaction transation);
    public List<Transaction> getAll ();
}
