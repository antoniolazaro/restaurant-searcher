package com.restaurant.searcher.application.service.job.impl;

import com.restaurant.searcher.application.service.dataloader.RestaurantDataLoaderService;
import com.restaurant.searcher.application.service.job.JobLoaderDataService;
import com.restaurant.searcher.domain.exceptions.internalserver.DataFileErrorException;
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
        log.debug("Start loadRestaurantsFromResource");
        try {
            restaurantDataLoaderService.loadData();
            log.debug("Data loaded");
        } catch (DataFileErrorException ex) {
            log.error("Error {}", ex);
            throw ex;
        }
        log.debug("Finished loadRestaurantsFromResource");
    }
}
