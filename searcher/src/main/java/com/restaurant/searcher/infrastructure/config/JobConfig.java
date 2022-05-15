package com.restaurant.searcher.infrastructure.config;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.restaurant.searcher.domain.service")
public class JobConfig {

    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
        var storageProvider = new InMemoryStorageProvider();
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }
}
