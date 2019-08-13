package com.n26.scheduler;

import java.util.stream.Collectors;

import com.n26.service.TransactionService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * I chose to use the scheduler so as not to have to validate the list elements on each add/get
 *  because if the application will have more operations, this will be some to concerned about it.
 */
@Component
public class TransactionScheduler {
    private TransactionService service = new TransactionService();
    
    //Every second
    @Scheduled(cron="*/1 * * * * *")
    public void removeExpiredTransactions (){
        service.findAll()
            .stream()
            .filter(service::isExpired)
            .forEach(service::remove);
    }
}
