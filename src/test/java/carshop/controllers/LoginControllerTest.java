package carshop.controllers;

import carshop.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldLoginUser() throws Exception {
        mockMvc
                .perform(formLogin("/login").user("admin@gmail.com").password("admin"));
    }

    @Test
    void shouldLogoutUser() throws Exception{
        mockMvc
                .perform(logout("/logout"));
    }

    @Test
    void shouldCreateUserGetRequest() throws Exception {
        mockMvc
                .perform(get("/sign-up"))
                .andExpect(model().attribute("user", new User()))
                .andDo(print()).andExpect(status().isOk());

    }

//    @Test
//    void shouldCreateUserPostRequest() throws Exception {
//        this.mockMvc.perform(post("/sign-up")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(new User("user@gmail.com", "user", "name", "surname", "USER", "0", 0))))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection());
//    }
}