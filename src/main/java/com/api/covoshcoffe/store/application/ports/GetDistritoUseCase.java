package com.api.covoshcoffe.store.application.ports;

import java.util.List;

import com.api.covoshcoffe.store.application.dto.response.DistritoDto;

public interface GetDistritoUseCase {
    List<DistritoDto> getAllDistritos();
}
