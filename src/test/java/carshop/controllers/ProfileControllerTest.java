package carshop.controllers;

import carshop.model.entity.Car;
import carshop.model.entity.User;
import carshop.model.enums.Roles;
import carshop.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserServiceImpl userService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void profile() throws Exception {
        this.mockMvc.perform(get("/profile")
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(model().attribute("user", new User("admin@gmail.com", "admin", "admin", "admin", "ADMIN", "0999999999", 0)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void edit() throws Exception {
        this.mockMvc.perform(post("/profile/edit")
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new User("admin@gmail.com", "admin", "admin", "admin", "ADMIN", "0999999999", 0)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void topUp() throws Exception {
        this.mockMvc.perform(post("/profile/top-up")
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new User("admin@gmail.com", "admin", "admin", "admin", "ADMIN", "0999999999", 0)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}