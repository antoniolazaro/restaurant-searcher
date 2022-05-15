package com.restaurant.searcher.interfaces.rest;

import com.restaurant.searcher.application.service.job.JobExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class JobControlController {

    @Autowired
    private JobExecutorService jobExecutorService;

    @GetMapping("/load-data-job")
    public String runJob() {
        jobExecutorService.enqueueLoadData();
        return "Job is enqueued.";

    }

}
