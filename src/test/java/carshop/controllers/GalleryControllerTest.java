package carshop.controllers;

import carshop.model.entity.Car;
import carshop.model.entity.Gallery;
import carshop.service.GalleryServiceImpl;
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

@WebMvcTest(GalleryController.class)
class GalleryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GalleryServiceImpl galleryService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getPhotosFromGallery() throws Exception {
        this.mockMvc.perform(get("/gallery/{page}",1)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(model().attribute("gallery", galleryService.getAllGalleries()))
                .andExpect(model().attribute("photo", new Gallery()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createAdvert() throws Exception {
        mockMvc.perform(post("/photo")
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Gallery(1L,1L,"asfdg"))))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    void updateGallery() throws Exception {
        this.mockMvc.perform(get("/photo/update/{id}",1L)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(model().attribute("photo", new Gallery(1L,1L,"asfdg")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateGallery() throws Exception {
        this.mockMvc.perform(post("/photo/update")
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Gallery(1L,1L,"assdfdg"))))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteGalleryById() throws Exception {
        this.mockMvc.perform(get("/photo/delete/{id}",1)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().is3xxRedirection());
    }
}