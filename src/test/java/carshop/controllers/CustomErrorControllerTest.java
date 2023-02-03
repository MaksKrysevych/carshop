package carshop.controllers;

import carshop.service.ErrorServiceImpl;
import org.junit.jupiter.api.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomErrorController.class)
class CustomErrorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ErrorServiceImpl errorService;

    @Test
    void shouldReturnErrorPage() throws Exception {
        this.mockMvc.perform(get("/error").with(user("admin@gmail.com")
                        .password("admin")
                        .roles("ADMIN")))
                .andDo(print()).andExpect(status().isOk());
    }
}