package com.api.covoshcoffe.store.application.ports;

import java.util.List;

import com.api.covoshcoffe.store.application.dto.response.LocalDto;

public interface GetLocalUseCase {
    List<LocalDto> getAllLocales();
}
