package carshop.service;

import carshop.model.entity.User;
import carshop.model.enums.Roles;
import carshop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("adscv", "sdV", "SDVV", "SDVV", Roles.USER, 4356576, 0));
        userList.add(new User("adscv", "sdV", "SDVV", "SDVV", Roles.ADMIN, 4356576, 0));
    }

    @Test
    void getAllUsers() {
        when(userRepository.getAllUsers()).thenReturn(userList);
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void getUserByEmail() {
        when(userRepository.getUserByEmail("adscv")).thenReturn(userList.get(0));
        assertEquals(userList.get(0), userService.getUserByEmail("adscv"));
    }

    @Test
    void createUser() {
        when(userRepository.createUser(new User("adscv", "sdV", "SDVV", "SDVV", Roles.USER, 4356576, 0))).thenReturn(userList.get(0));
        assertEquals(userList.get(0), userService.createUser(new User("adscv", "sdV", "SDVV", "SDVV", Roles.USER, 4356576, 0)));
    }

    @Test
    void updateUser() {
        when(userRepository.updateUser(new User("adscv", "sdV", "SDVV", "SDVV", Roles.USER, 4356576, 0))).thenReturn(userList.get(1));
        assertEquals(userList.get(1).getRole(), userService.updateUser(new User("adscv", "sdV", "SDVV", "SDVV", Roles.ADMIN, 4356576, 0)).getRole());
    }

    @Test
    void deleteUserByEmail() {
        userRepository.deleteUserByEmail("adscv");
        assertEquals(0, userService.getAllUsers().size());
        verify(userRepository).deleteUserByEmail("adscv");
    }
}