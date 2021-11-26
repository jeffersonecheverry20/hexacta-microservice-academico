package com.hexacta.microservice.consultservice.suscribe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexacta.microservice.consultservice.entity.Metric;
import com.hexacta.microservice.consultservice.model.MessageUser;
import com.hexacta.microservice.consultservice.repository.MetricRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MetricSubscribe implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricSubscribe.class);

    @Autowired
    private MetricRepository metricRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try{
            MessageUser user = objectMapper.readValue(message.getBody(), MessageUser.class);
            String operation = user.getOperation();
            Optional<Metric> metric = metricRepository.findById(String.valueOf(user.getUser().getUserId()));

            if(!metric.isPresent()){
                metricRepository.save(Metric.builder()
                        .userId(String.valueOf(user.getUser().getUserId()))
                        .save(isSave(operation) ? 1 : 0)
                        .update(isUpdate(operation) ? 1 : 0)
                        .delete(isDelete(operation) ? 1 : 0)
                        .date(new Date())
                        .build());
                return;
            }

            if(isSave(operation)){
                metric.get().setSave(metric.get().getSave()+1);
            }else if(isUpdate(operation)){
                metric.get().setUpdate(metric.get().getUpdate()+1);
            } else if(isDelete(operation)){
                metric.get().setDelete(metric.get().getDelete()+1);
            }

            metric.get().setDate(new Date());
            metricRepository.save(metric.get());

        }catch (Exception exception){
            LOGGER.info("exception "+exception.getMessage());
        }
    }

    private boolean isSave(String operation){
        return operation.equalsIgnoreCase("save");
    }

    private boolean isUpdate(String operation){
        return operation.equalsIgnoreCase("update");
    }

    private boolean isDelete(String operation){
        return operation.equalsIgnoreCase("delete");
    }
}
