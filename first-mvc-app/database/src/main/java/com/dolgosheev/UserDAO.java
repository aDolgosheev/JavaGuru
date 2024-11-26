package com.dolgosheev;

import java.util.Optional;

public class UserDAO {
    public Optional<User> findById(Long id) {
        User user = new User();
        user.setName("Aleksandr");
        return Optional.of(user);
    }
}
