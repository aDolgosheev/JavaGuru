package com.dolgosheev;

import java.util.Optional;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public Optional<UserDto> getUser(Long id) {
        return userDAO.findById(id).map(it -> new UserDto(it.getName()));
    }
}
