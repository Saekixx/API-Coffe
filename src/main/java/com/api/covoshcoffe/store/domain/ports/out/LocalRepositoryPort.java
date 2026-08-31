package com.api.covoshcoffe.store.domain.ports.out;

import com.api.covoshcoffe.store.domain.model.Local;

public interface LocalRepositoryPort {
    Local save(Local local);
    

    Local findById(Long id);

    boolean existsByNombre(String nombre);
    
}
