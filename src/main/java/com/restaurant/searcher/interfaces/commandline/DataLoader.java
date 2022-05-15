package com.restaurant.searcher.interfaces.commandline;

import com.restaurant.searcher.application.service.job.JobExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    private JobExecutorService jobExecutorService;

    @Override
    public void run(String... args) {
        log.info("jobExecutorService.enqueueLoadData ");
        jobExecutorService.enqueueLoadData();
        log.info("Finish jobExecutorService.enqueueLoadData ");
    }
}
