package com.n26.dto;

import java.math.BigDecimal;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

//@Data I had some lombok issue in online IDE, so I'm using the methods getters and setters
public class Transaction {
    
    @NotNull
    @Positive
    private BigDecimal amount;
    
    @NotNull
    @PastOrPresent
    private Instant timestamp;
    
    public Transaction (BigDecimal amount, Instant timestamp){
        this.amount = amount;
        this.timestamp = timestamp;
    }
    
    public void setAmount (BigDecimal amount){
        this.amount = amount;
    }
    
    public BigDecimal getAmount (){
        return amount;
    }
    
    public void setTimestamp (Instant timestamp){
        this.timestamp = timestamp;
    }
    
    public Instant getTimestamp (){
        return timestamp;
    }
}
