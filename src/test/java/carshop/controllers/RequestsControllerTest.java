package carshop.controllers;

import carshop.model.entity.Storage;
import carshop.service.RequestServiceImpl;
import carshop.service.StorageServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RequestsController.class)
class RequestsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RequestServiceImpl requestService;

    @MockBean
    StorageServiceImpl storageService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getRequests() throws Exception {
        this.mockMvc.perform(get("/requests/{page}",1)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(model().attribute("requests", requestService.getAllRequests()))
                .andExpect(model().attribute("storages", storageService.getAllStorageAdverts()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void changeStatusOfRequest() throws Exception {
        this.mockMvc.perform(post("/requests")
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Storage(1L, "AVAILABLE")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteRequestById() throws Exception {
        this.mockMvc.perform(get("/request/delete/{id}",1L)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    void reserveCarById() throws Exception {
        this.mockMvc.perform(get("/request/reserve/{id}",1L)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }
}