package com.hexacta.microservice.consultservice.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.hexacta.microservice.consultservice.entity.Metric;
import com.hexacta.microservice.consultservice.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MetricRepositoryImpl implements MetricRepository {

    @Autowired
    private DynamoDBMapper mapper;

    @Override
    public Metric saveMetric(Metric metric) {
        mapper.save(metric);
        return metric;
    }

    @Override
    public Metric getMetric(String userId) {
        return mapper.load(Metric.class, userId);
    }

    @Override
    public Optional<List<Metric>> getMetrics() {
        return Optional.of(mapper.scan(Metric.class, new DynamoDBScanExpression()));
    }

}
