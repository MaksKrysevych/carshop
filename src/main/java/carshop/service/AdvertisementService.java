package carshop.service;

import carshop.model.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {
    List<Advertisement> getAllAdverts();

    Advertisement getAdvertById(Long id);

    Advertisement createAdvert(Advertisement advertisement);

    Advertisement updateAdvert(Advertisement advertisement);

    void deleteAdvertById(Long id);
}