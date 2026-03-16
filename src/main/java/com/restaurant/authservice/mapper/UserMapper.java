package com.restaurant.authservice.mapper;

import com.restaurant.authservice.dto.LoginRequest;
import com.restaurant.authservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(LoginRequest request);

}