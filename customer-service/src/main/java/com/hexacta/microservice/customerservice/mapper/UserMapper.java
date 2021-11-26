package com.hexacta.microservice.customerservice.mapper;

import com.hexacta.microservice.customerservice.dto.UserDto;
import com.hexacta.microservice.customerservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roleDto",
    expression = "java(com.hexacta.microservice.customerservice.mapper.RoleMapper.INSTANCE.roleToRoleDto(user.getRole()))")
    UserDto userToUserDto(User user);

    @Mapping(target = "role",
    expression = "java(com.hexacta.microservice.customerservice.mapper.RoleMapper.INSTANCE.roleDtoToRole(userDto.getRoleDto()))")
    User userDtoToUser(UserDto userDto);

    default List<UserDto> listUserToListUserDto(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        userList.forEach(user -> userDtoList.add(INSTANCE.userToUserDto(user)));
        return userDtoList;
    }

}
