package carshop.service;

import carshop.model.entity.Advertisement;
import carshop.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public List<Advertisement> getAllAdverts() {
        return advertisementRepository.getAllAdverts();
    }

    @Override
    public List<Advertisement> getAdvertsByPage(int pageNo, int advertsPerPage,  String sorting) {
        return advertisementRepository.getAdvertsByPage(pageNo, advertsPerPage, sorting);
    }

    @Override
    public Advertisement getAdvertById(Long id) {
        return advertisementRepository.getAdvertById(id);
    }

    @Override
    public Advertisement createAdvert(Advertisement advertisement) {
        return advertisementRepository.createAdvert(advertisement);
    }

    @Override
    public Advertisement updateAdvert(Advertisement advertisement) {
        return advertisementRepository.updateAdvert(advertisement);
    }

    @Override
    public void deleteAdvertById(Long id) {
        advertisementRepository.deleteAdvertById(id);
    }
}