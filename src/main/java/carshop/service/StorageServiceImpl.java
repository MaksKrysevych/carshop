package carshop.service;

import carshop.model.entity.Storage;
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
    public List<Storage> getAllStorages() {
        return storageRepository.getAllStorages();
    }

    @Override
    public Storage getStorageById(Long id) {
        return storageRepository.getStorageById(id);
    }

    @Override
    public Storage createStorage(Storage storage) {
        return storageRepository.createStorage(storage);
    }

    @Override
    public Storage updateStorage(Storage storage) {
        return storageRepository.updateStorage(storage);
    }

    @Override
    public void deleteStorageById(Long id) {
        storageRepository.deleteStorageById(id);
    }
}
