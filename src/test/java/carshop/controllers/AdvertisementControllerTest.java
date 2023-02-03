package carshop.controllers;

import carshop.model.entity.Advertisement;
import carshop.model.entity.Car;
import carshop.service.AdvertisementServiceImpl;
import carshop.service.StorageServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdvertisementController.class)
class AdvertisementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AdvertisementServiceImpl advertisementService;

    @MockBean
    StorageServiceImpl storageService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAdverts() throws Exception {
        this.mockMvc.perform(get("/advertisements/{page}",1)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(model().attribute("adverts", advertisementService.getAllAdverts()))
                .andExpect(model().attribute("advertisement", new Advertisement()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createAdvert() throws Exception {
        mockMvc.perform(post("/advertisement")
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Advertisement(1L,1L,1L,10000,"asfdg")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateAdvert() throws Exception {
        this.mockMvc.perform(get("/advertisements/update/{id}",1)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andDo(print())
                .andExpect(model().attribute("advert", new Advertisement(1L,1L,1L,10000,"asfdg")))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateAdvert() throws Exception {
        this.mockMvc.perform(post("/advertisements/update")
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Advertisement(1L,1L,1L,10000,"asfdg")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteAdvertById() throws Exception {
        this.mockMvc.perform(get("/advertisements/delete/{id}", 1L)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().is3xxRedirection());
    }
}