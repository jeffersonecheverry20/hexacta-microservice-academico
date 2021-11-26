package com.hexacta.microservice.customerservice.service.impl;

import com.hexacta.microservice.customerservice.dto.UserDto;
import com.hexacta.microservice.customerservice.entity.User;
import com.hexacta.microservice.customerservice.mapper.UserMapper;
import com.hexacta.microservice.customerservice.model.MessageUser;
import com.hexacta.microservice.customerservice.model.Response;
import com.hexacta.microservice.customerservice.publish.UserPublisher;
import com.hexacta.microservice.customerservice.repository.UserRepository;
import com.hexacta.microservice.customerservice.service.UserService;
import com.hexacta.microservice.customerservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPublisher userPublisher;

    @Override
    public ResponseEntity<Response> saveUser(UserDto userDto) {
        try{
            Optional<User> userBd = userRepository.findByUsername(userDto.getUsername());
            if(userBd.isPresent()){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(createResponse(Constants.CODE_ERROR, Constants.MESSAGE_ERROR, "User already exist"));
            }
            User user = userRepository.save(userMapper.userDtoToUser(userDto));
            userPublisher.publish(MessageUser.builder().user(userMapper.userToUserDto(user)).operation(Constants.HTTP_SAVE).build());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, userMapper.userToUserDto(user)));
        }catch (Exception exception){
            LOGGER.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_ERROR, Constants.MESSAGE_ERROR, exception.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Response> getUser(int userId) {
        try{
            User user = userRepository.getById(userId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, userMapper.userToUserDto(user)));
        }catch (Exception exception){
            LOGGER.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_ERROR, Constants.MESSAGE_ERROR, exception.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Response> getUsers() {
        try {
            List<User> userList = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, userMapper.listUserToListUserDto(userList)));
        }catch (Exception exception){
            LOGGER.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_ERROR, Constants.MESSAGE_ERROR, exception.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Response> updateUser(UserDto userDto) {
        try{
            Optional<User> userBd = userRepository.findByUsername(userDto.getUsername());
            if(!userBd.isPresent()){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(createResponse(Constants.CODE_ERROR, Constants.MESSAGE_ERROR, "User don't exist"));
            }
            User user = userRepository.save(userMapper.userDtoToUser(userDto));
            userPublisher.publish(MessageUser.builder().user(userMapper.userToUserDto(user)).operation(Constants.HTTP_UPDATE).build());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, userMapper.userToUserDto(user)));
        }catch (Exception exception){
            LOGGER.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_ERROR, Constants.MESSAGE_ERROR, exception.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Response> deleteUser(int userId) {
        try{
            User user = userRepository.getById(userId);
            if(Objects.isNull(user)){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(createResponse(Constants.CODE_ERROR, Constants.MESSAGE_ERROR, "User don't exist"));
            }
            userPublisher.publish(MessageUser.builder().user(userMapper.userToUserDto(user)).operation(Constants.HTTP_DELETE).build());
            userRepository.delete(user);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, userMapper.userToUserDto(user)));
        }catch (Exception exception){
            LOGGER.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(createResponse(Constants.CODE_ERROR, Constants.MESSAGE_ERROR, exception.getMessage()));
        }
    }

    private Response createResponse(int code, String message, Object object){
        return Response.builder().code(code).message(message).body(object).build();
    }

}
