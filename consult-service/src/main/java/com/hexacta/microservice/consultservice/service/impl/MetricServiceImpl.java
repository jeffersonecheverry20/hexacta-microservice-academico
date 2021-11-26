package com.hexacta.microservice.consultservice.service.impl;

import com.hexacta.microservice.consultservice.entity.Metric;
import com.hexacta.microservice.consultservice.model.Response;
import com.hexacta.microservice.consultservice.repository.MetricRepository;
import com.hexacta.microservice.consultservice.service.MetricService;
import com.hexacta.microservice.consultservice.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetricServiceImpl implements MetricService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricServiceImpl.class);

    @Autowired
    MetricRepository metricRepository;

    @Override
    public ResponseEntity<Response> getMetric(String userId) {
        try{
            Optional<Metric> metric = metricRepository.findById(userId);
            if(!metric.isPresent()){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(createResponse(Constant.CODE_ERROR, Constant.MESSAGE_ERROR, "User don't exist"));
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constant.CODE_SUCCESS, Constant.MESSAGE_SUCCESS, metric));
        }catch (Exception exception){
            LOGGER.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constant.CODE_ERROR, Constant.MESSAGE_ERROR, exception.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Response> getMetrics() {
        try{
            List<Metric> metricList = metricRepository.findAll();
            if(metricList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(createResponse(Constant.CODE_ERROR, Constant.MESSAGE_ERROR, "Users don't exist"));
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constant.CODE_SUCCESS, Constant.MESSAGE_SUCCESS, metricList));
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constant.CODE_ERROR, Constant.MESSAGE_ERROR, exception.getMessage()));
        }
    }

    private Response createResponse(int code, String message, Object body){
        return Response.builder().code(code).message(message).body(body).build();
    }
}
