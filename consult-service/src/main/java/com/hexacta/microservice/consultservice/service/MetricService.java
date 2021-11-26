package com.hexacta.microservice.consultservice.service;

import com.hexacta.microservice.consultservice.entity.Metric;
import com.hexacta.microservice.consultservice.model.Response;
import org.springframework.http.ResponseEntity;

public interface MetricService {

    ResponseEntity<Response> getMetric(String userId);

    ResponseEntity<Response> getMetrics();

}
