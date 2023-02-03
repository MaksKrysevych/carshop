package carshop.controllers;

import carshop.model.entity.Advertisement;
import carshop.model.entity.Car;
import carshop.model.entity.Gallery;
import carshop.service.AdvertisementServiceImpl;
import carshop.service.CarServiceImpl;
import carshop.service.GalleryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatalogController.class)
class CatalogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AdvertisementServiceImpl advertisementService;

    @MockBean
    CarServiceImpl carService;

    @MockBean
    GalleryServiceImpl galleryService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        advertisementService.createAdvert((new Advertisement(1L,1L,1L,10000,"asfdg")));
        carService.createCar(new Car(1L, "as", "as", "as", "as", Date.valueOf("2022-02-02")));
        galleryService.createGallery(new Gallery(1L,1L,"asfdg"));
    }

    @Test
    void catalog() throws Exception {
        this.mockMvc.perform(get("/catalog/{page}",1)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(model().attribute("adverts", advertisementService.getAllAdverts()))
                .andExpect(model().attribute("cars", carService.getAllCars()))
                .andExpect(model().attribute("galleries", galleryService.getAllGalleries()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void details() throws Exception {
        this.mockMvc.perform(get("/details/{id}",1L)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(model().attribute("advert", advertisementService.getAdvertById(1L)))
                .andExpect(model().attribute("car", carService.getCarById(advertisementService.getAdvertById(1L).getCarId())))
                .andExpect(model().attribute("gallery", galleryService.getGalleryById(advertisementService.getAdvertById(1L).getPhotoId())))
                .andDo(print())
                .andExpect(status().isOk());
    }
}