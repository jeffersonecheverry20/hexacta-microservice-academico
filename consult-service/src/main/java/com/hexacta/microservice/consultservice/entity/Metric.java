package com.hexacta.microservice.consultservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(value = "metrics")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Metric implements Serializable {

    @Id
    private String userId;
    private int save;
    private int update;
    private int delete;
    private Date date;

}
