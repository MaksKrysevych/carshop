package carshop.service;

import carshop.model.entity.Storage;

import java.util.List;

public interface StorageService {
    List<Storage> getAllStorageCars();

    Storage getStorageCarById(Long id);

    Storage createStorageCar(Storage storage);

    Storage updateStorageCar(Storage storage);

    void deleteStorageCarById(Long id);
}