package com.api.covoshcoffe.store.application.ports;

import java.util.List;

import com.api.covoshcoffe.store.domain.model.Local;

public interface GetStoreUseCase {
    List<Local> getAllActiveStores();

    Local getStoreById(Integer id);
}
