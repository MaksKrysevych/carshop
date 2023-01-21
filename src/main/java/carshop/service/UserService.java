package carshop.service;

import carshop.model.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByEmail(String email);

    User createUser(User user);

    User signUpUser(User user);

    User updateUser(User user);

    User editUser(User editedUser, Authentication authentication);

    User topUp(User user, Authentication authentication);

    void deleteUserByEmail(String email);
}