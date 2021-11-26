package com.hexacta.microservice.customerservice.model;

import com.hexacta.microservice.customerservice.dto.UserDto;
import com.hexacta.microservice.customerservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageUser {

    private UserDto user;
    private String operation;

}
