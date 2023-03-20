package com.jrendulic.Vrello.DTO;

import com.jrendulic.Vrello.model.User;
import com.jrendulic.Vrello.model.Role;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class UserMapper {
    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(toList())
        );
    }
}
