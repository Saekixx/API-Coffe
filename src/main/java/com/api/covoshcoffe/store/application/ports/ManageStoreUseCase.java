package com.api.covoshcoffe.store.application.ports;

import java.util.List;

import com.api.covoshcoffe.store.application.dto.request.CreateLocalCommand;
import com.api.covoshcoffe.store.application.dto.request.UpdateLocalCommand;
import com.api.covoshcoffe.store.domain.model.Local;

public interface ManageStoreUseCase {
    List<Local> getAllStores();

    Local createStore(CreateLocalCommand command);

    Local updateStore(Integer id, UpdateLocalCommand command);

    String toggleStoreStatus(Integer id);
}
