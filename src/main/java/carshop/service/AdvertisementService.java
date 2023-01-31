package carshop.service;

import carshop.model.entity.Advertisement;
import org.springframework.ui.Model;

import java.util.List;

public interface AdvertisementService {
    List<Advertisement> getAllAdverts();

    List<Advertisement> getAdvertsByPage(int page, int advertsPerPage, String sorting);

    Advertisement getAdvertById(Long id);

    Advertisement createAdvert(Advertisement advertisement);

    Advertisement updateAdvert(Advertisement advertisement);

    void deleteAdvertById(Long id);
}