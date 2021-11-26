package com.hexacta.microservice.consultservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private int roleId;
    private String role;
    private Set<UserDto> userDto;

}
