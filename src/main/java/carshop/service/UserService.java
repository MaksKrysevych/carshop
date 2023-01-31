package carshop.service;

import carshop.model.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    List<User> getAllUsersByPage(int page, int requestsPerPage);

    User getUserByEmail(String email);

    User createUser(User user);

    User signUpUser(User user);

    User updateUser(User user);

    User updateRoleUsers(User user);

    User editUser(User editedUser, Authentication authentication);

    User topUp(User user, Authentication authentication);

    void deleteUserByEmail(String email);
}