package com.n26.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StatisticsBuilder {
    private BigDecimal sum;
    private BigDecimal avg;
    private BigDecimal max;
    private BigDecimal min;
    private Long count;
    
    public StatisticsBuilder (){
        this.sum = BigDecimal.ZERO;
        this.avg = BigDecimal.ZERO;
        this.max = BigDecimal.ZERO;
        //The biggest double possible to be replaced to the first transaction.min value
        this.min = BigDecimal.valueOf(Double.MAX_VALUE);
        this.count = 0L;
        build();
    }
    
    public StatisticsBuilder sum (BigDecimal amount){
        this.sum = this.sum.add(amount);
        return this;
    }
    
    public StatisticsBuilder avg (){
        this.avg = this.sum.divide(BigDecimal.valueOf(this.count), 2, RoundingMode.HALF_UP);
        return this; 
    }
    
    public StatisticsBuilder min (BigDecimal amount){
        this.min = this.min.min(amount);
        return this;
    }
    
    public StatisticsBuilder max (BigDecimal amount){
        this.max = this.max.max(amount);
        return this;
    }
    
    public StatisticsBuilder count (){
        this.count += 1;
        return this;
    }
    
    private BigDecimal roundHalfUpTwoPlaces (BigDecimal value){
        return value.setScale(2);
    }
    
    public StatisticsBuilder roundHalfUp (){
        if (BigDecimal.valueOf(Double.MAX_VALUE).equals(this.min)){
            this.min = BigDecimal.ZERO;
        }
        
        this.sum = roundHalfUpTwoPlaces(sum);
        this.avg = roundHalfUpTwoPlaces(avg);
        this.min = roundHalfUpTwoPlaces(min);
        this.max = roundHalfUpTwoPlaces(max);
        
        return this;
    }
    
    public StatisticsResponse build (){
        new Statistics(sum, avg, max, min, count);
        
        String sum = this.sum.toString();
        String avg = this.avg.toString();
        String min = this.min.toString();
        String max = this.max.toString();
        return new StatisticsResponse(sum, avg, min, max, count);
    }
    
}
