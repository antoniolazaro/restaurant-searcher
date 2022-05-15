package com.restaurant.searcher.application.service.job.impl;

import com.restaurant.searcher.application.service.dataloader.RestaurantDataLoaderService;
import com.restaurant.searcher.application.service.job.JobLoaderDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class JobLoaderDataServiceImpl implements JobLoaderDataService {
    private final RestaurantDataLoaderService restaurantDataLoaderService;

    @Override
    @Job(name = "Job to Load Restaurants from resource")
    public void loadRestaurantsFromResource() {
        log.info("Start loadRestaurantsFromResource");
        try {
            restaurantDataLoaderService.loadData();
        } catch (Exception ex) {
            log.error("Error {}", ex);
        }
        log.info("Finished loadRestaurantsFromResource");
    }
}
