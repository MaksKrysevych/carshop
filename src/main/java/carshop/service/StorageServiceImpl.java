package carshop.service;

import carshop.model.entity.Storage;
import carshop.model.enums.Statuses;
import carshop.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService{

    private final StorageRepository storageRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public List<Storage> getAllStorageAdverts() {
        return storageRepository.getAllStorages();
    }

    @Override
    public Storage getStorageAdvertById(Long id) {
        return storageRepository.getStorageById(id);
    }

    @Override
    public Storage createStorageAdvert(Storage storage) {
        return storageRepository.createStorage(storage);
    }

    @Override
    public Storage updateStorageAdvert(Storage storage) {
        return storageRepository.updateStorage(storage);
    }

    @Override
    public Storage reserveCarById(Long id) {
        Storage advertStatus = storageRepository.getStorageById(id);
        advertStatus.setStatus(Statuses.BOOKED.toString());

        return storageRepository.updateStorage(advertStatus);
    }

    @Override
    public void deleteStorageAdvertById(Long id) {
        storageRepository.deleteStorageById(id);
    }
}