package com.api.covoshcoffe.catalog.application.services;

import java.util.List;

import com.api.covoshcoffe.catalog.application.dto.response.CategoriaDto;
import com.api.covoshcoffe.catalog.domain.dtos.GrupoPersonalizacionDto;
import com.api.covoshcoffe.catalog.domain.dtos.OpcionPersonalizacionDto;
import com.api.covoshcoffe.catalog.domain.dtos.ProductoGrupoDto;
import com.api.covoshcoffe.catalog.domain.dtos.ProductsWithFavoritesDto;
import com.api.covoshcoffe.catalog.domain.ports.out.CategoriaRepositoryPort;
import com.api.covoshcoffe.catalog.domain.ports.out.GrupoPersonalizacionRepositoryPort;
import com.api.covoshcoffe.catalog.domain.ports.out.PersonalizacionRepositoryPort;
import org.springframework.stereotype.Service;

import com.api.covoshcoffe.catalog.application.dto.response.ProductoDto;
import com.api.covoshcoffe.catalog.application.ports.in.GetProductoUseCase;
import com.api.covoshcoffe.catalog.domain.ports.out.ProductoRepositoryPort;

@Service
public class GetProductoService implements GetProductoUseCase {
    private final ProductoRepositoryPort productoRepositoryPort;
    private final CategoriaRepositoryPort categoriaRepositoryPort;
    private final GrupoPersonalizacionRepositoryPort grupoPersonalizacionRepositoryPort;
    private final PersonalizacionRepositoryPort opcionPersonalizacionRepositoryPort;

    public GetProductoService(ProductoRepositoryPort productoRepositoryPort, CategoriaRepositoryPort categoriaRepositoryPort, GrupoPersonalizacionRepositoryPort grupoPersonalizacionRepositoryPort, PersonalizacionRepositoryPort opcionPersonalizacionRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
        this.categoriaRepositoryPort = categoriaRepositoryPort;
        this.grupoPersonalizacionRepositoryPort = grupoPersonalizacionRepositoryPort;
        this.opcionPersonalizacionRepositoryPort = opcionPersonalizacionRepositoryPort;
    }

    @Override
    public List<ProductoDto> getAllProductos() {

        return productoRepositoryPort.findAll().stream()
                .map(producto -> new ProductoDto(
                        producto.id(),
                        producto.nombre(),
                        producto.descripcion(),
                        producto.precioBase(),
                        producto.categoria().id(),
                        producto.isNuevo() ? 1 : 0,
                        producto.isFrecuente() ? 1 : 0
                    ))
                .toList();
    }

    @Override
    public List<CategoriaDto> getAllCategorias() {
        return categoriaRepositoryPort.findAll().stream()
                .map(categoria -> new CategoriaDto(
                        categoria.id(),
                        categoria.nombre()
                    ))
                .toList();
    }

    @Override
    public List<ProductsWithFavoritesDto> findAllWithFavorites(Integer usuarioId) {
        return productoRepositoryPort.findAllWithFavorites(usuarioId);
    }

    @Override
    public List<GrupoPersonalizacionDto> getGrupoPersonalizacion() {
        return grupoPersonalizacionRepositoryPort.getGrupoPersonalizacion();
    }

    @Override
    public List<OpcionPersonalizacionDto> getOpcionPersonalizacion() {
        return opcionPersonalizacionRepositoryPort.getOpcionPersonalizacion();
    }

    @Override
    public List<ProductoGrupoDto> findAllWithGruposAndOpciones() {
        return productoRepositoryPort.findAllWithGruposAndOpciones();
    }
}
