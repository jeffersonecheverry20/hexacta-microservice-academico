package com.hexacta.microservice.customerservice.service;

import com.hexacta.microservice.customerservice.dto.UserDto;
import com.hexacta.microservice.customerservice.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<Response> saveUser(UserDto userDto);

    ResponseEntity<Response> getUser(int userId);

    ResponseEntity<Response> getUsers();

    ResponseEntity<Response> updateUser(UserDto userDto);

    ResponseEntity<Response> deleteUser(int userId);

}
