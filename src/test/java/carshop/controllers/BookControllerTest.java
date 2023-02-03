package carshop.controllers;

import carshop.service.RequestServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RequestServiceImpl requestService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void book() throws Exception {
        this.mockMvc.perform(get("/book/{id}",1L)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}