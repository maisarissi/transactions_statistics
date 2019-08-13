package com.n26.dto;

public class StatisticsResponse {
    private String sum;
    private String avg;
    private String min;
    private String max;
    private Long count;
    
    protected StatisticsResponse (String sum, String avg, String min, String max, Long count){
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
        this.count = count;
    }
    
    public String getSum (){
        return this.sum;
    }
    
    public String getAvg (){
        return this.avg;
    }
    
    public String getMin (){
        return this.min;
    }
    
    public String getMax (){
        return this.max;
    }
    
    public Long getCount (){
        return this.count;
    }
}
