package com.hexacta.microservice.consultservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int userId;
    private String name;
    private String lastname;
    private String username;
    private String password;
    private RoleDto roleDto;

}
