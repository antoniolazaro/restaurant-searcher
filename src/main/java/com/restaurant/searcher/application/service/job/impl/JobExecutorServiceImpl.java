package com.restaurant.searcher.application.service.job.impl;

import com.restaurant.searcher.application.service.job.JobLoaderDataService;
import com.restaurant.searcher.application.service.job.JobExecutorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class JobExecutorServiceImpl implements JobExecutorService {

    private final JobScheduler jobScheduler;

    private final JobLoaderDataService jobLoaderDataService;

    public void enqueueLoadData() {
        jobScheduler.enqueue(() -> jobLoaderDataService.loadRestaurantsFromResource());
    }

}
