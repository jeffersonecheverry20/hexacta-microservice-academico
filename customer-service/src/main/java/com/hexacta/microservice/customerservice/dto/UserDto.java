package com.hexacta.microservice.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private int userId;
    private String name;
    private String lastname;
    private String username;
    private String password;
    private RoleDto roleDto;

}
