package com.hexacta.microservice.consultservice.repository;

import com.hexacta.microservice.consultservice.entity.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetricRepository extends MongoRepository<Metric, String> {

}
