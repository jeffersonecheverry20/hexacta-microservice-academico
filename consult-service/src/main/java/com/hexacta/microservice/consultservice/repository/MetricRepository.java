package com.hexacta.microservice.consultservice.repository;

import com.hexacta.microservice.consultservice.entity.Metric;

import java.util.List;
import java.util.Optional;

public interface MetricRepository {

    Metric saveMetric(final Metric metric);

    Metric getMetric(final String userId);

    Optional<List<Metric>> getMetrics();

}
