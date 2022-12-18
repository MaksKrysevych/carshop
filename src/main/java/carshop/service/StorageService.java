package carshop.service;

import carshop.model.entity.Storage;

import java.util.List;

public interface StorageService {
    List<Storage> getAllStorages();

    Storage getStorageById(Long id);

    Storage createStorage(Storage storage);

    Storage updateStorage(Storage storage);

    void deleteStorageById(Long id);
}