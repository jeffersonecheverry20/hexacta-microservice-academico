package com.hexacta.microservice.consultservice.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@DynamoDBTable(tableName = "metrics")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Metric implements Serializable {

    @DynamoDBHashKey(attributeName = "userId")
    private String userId;
    @DynamoDBAttribute
    private int save;
    @DynamoDBAttribute
    private int update;
    @DynamoDBAttribute
    private int delete;
    @DynamoDBAttribute
    private Date date;

}
