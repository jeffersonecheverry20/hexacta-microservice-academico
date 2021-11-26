package com.hexacta.microservice.consultservice.controller;

import com.hexacta.microservice.consultservice.model.Response;
import com.hexacta.microservice.consultservice.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "metric")
public class MetricController {

    @Autowired
    MetricService metricService;

    @GetMapping(value = "/getMetric/{userId}")
    public ResponseEntity<Response> getUser(@PathVariable(value = "userId") String userId){
        return metricService.getMetric(userId);
    }

    @GetMapping(value = "/getMetrics")
    public ResponseEntity<Response> getUsers(){
        return metricService.getMetrics();
    }

}
