package com.api.covoshcoffe.order.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.order.application.dtos.response.OpcionPersonalizacionDto;

public interface GetOpcionPersonalizacionUseCase {
    List<OpcionPersonalizacionDto> getAllOpcionesPersonalizacion();
}
