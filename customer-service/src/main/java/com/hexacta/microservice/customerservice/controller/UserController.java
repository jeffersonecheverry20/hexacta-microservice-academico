package com.hexacta.microservice.customerservice.controller;

import com.hexacta.microservice.customerservice.dto.UserDto;
import com.hexacta.microservice.customerservice.model.Response;
import com.hexacta.microservice.customerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/saveUser")
    public ResponseEntity<Response> saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @GetMapping(value = "/getUser/{userId}")
    public ResponseEntity<Response> getUser(@PathVariable(value = "userId") int userId){
        return userService.getUser(userId);
    }

    @GetMapping(value = "/getUsers")
    public ResponseEntity<Response> getUsers(){
        return userService.getUsers();
    }

    @PutMapping(value = "/updateUser")
    public ResponseEntity<Response> updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    @DeleteMapping(value = "/deleteUser/{userId}")
    public ResponseEntity<Response> deleteUser(@PathVariable(value = "userId") int userId){
        return userService.deleteUser(userId);
    }

}
