package carshop.service;

import carshop.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByEmail(String email);

    User createUser(User user);

    User updateUser(User user);

    void deleteUserByEmail(String email);
}