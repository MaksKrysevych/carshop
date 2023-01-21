package carshop.service;

import carshop.model.entity.Storage;

import java.util.List;

public interface StorageService {
    List<Storage> getAllStorageAdverts();

    Storage getStorageAdvertById(Long id);

    Storage createStorageAdvert(Storage storage);

    Storage updateStorageAdvert(Storage storage);

    Storage reserveCarById(Long id);

    void deleteStorageAdvertById(Long id);
}