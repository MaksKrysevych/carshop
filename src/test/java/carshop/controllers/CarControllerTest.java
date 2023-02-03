package carshop.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import carshop.model.entity.Car;
import carshop.service.CarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.sql.Date;

@WebMvcTest(CarController.class)
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CarServiceImpl carService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getCars() throws Exception {
        this.mockMvc.perform(get("/cars/{page}",1)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(model().attribute("cars", carService.getAllCars()))
                .andExpect(model().attribute("car", new Car()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createCar() throws Exception {
        mockMvc.perform(post("/car")
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carService.createCar(new Car(1L, "Sedan", "Audi", "A4", "Standard", Date.valueOf("2022-02-02")))))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateCar() throws Exception {
        this.mockMvc.perform(get("/cars/update/{id}",1L)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andDo(print())
                .andExpect(model().attribute("car", new Car(1L, "Sedan", "Audi", "A4", "Standard", Date.valueOf("2022-02-02"))))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCar() throws Exception {
             this.mockMvc.perform(post("/car/update")
                             .with(user("admin@gmail.com")
                                     .password("admin")
                                     .roles("ADMIN"))
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(objectMapper.writeValueAsString(new Car(1L, "Sedan", "Audi", "A4", "Standard", Date.valueOf("2022-02-02"))))
                             .accept(MediaType.APPLICATION_JSON))
                     .andDo(print())
                     .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteCar() throws Exception {
        this.mockMvc.perform(get("/cars/delete/{id}", 1L)
                        .with(user("admin@gmail.com")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().is3xxRedirection());
    }
}