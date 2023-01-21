package carshop.service;

import carshop.model.entity.User;
import carshop.model.enums.Roles;
import carshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public User signUpUser(User user) {
        user.setRole(Roles.USER.toString());
        user.setAccount(0);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userRepository.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public User editUser(User editedUser, Authentication authentication) {
        User oldUser = userRepository.getUserByEmail(authentication.getName());
        oldUser.setName(editedUser.getName());
        oldUser.setSurname(editedUser.getSurname());
        oldUser.setPhone(editedUser.getPhone());

        return userRepository.updateUser(oldUser);
    }

    @Override
    public User topUp(User user, Authentication authentication) {
        User oldUsersAccount = userRepository.getUserByEmail(authentication.getName());
        oldUsersAccount.setAccount(user.getAccount() + oldUsersAccount.getAccount());

        return userRepository.updateUser(oldUsersAccount);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }
}
