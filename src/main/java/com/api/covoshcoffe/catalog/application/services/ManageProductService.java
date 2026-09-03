package com.api.covoshcoffe.catalog.application.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.api.covoshcoffe.catalog.application.dto.request.CreateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.request.UpdateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.response.CategoryResponse;
import com.api.covoshcoffe.catalog.application.dto.response.ProductResponse;
import com.api.covoshcoffe.catalog.application.ports.in.ManageProductUseCase;
import com.api.covoshcoffe.catalog.domain.model.Categoria;
import com.api.covoshcoffe.catalog.domain.model.Producto;
import com.api.covoshcoffe.catalog.domain.ports.out.CategoriaRepositoryPort;
import com.api.covoshcoffe.catalog.domain.ports.out.ProductoRepositoryPort;
import com.api.covoshcoffe.common.application.ports.output.StoragePort;
import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;

@Service
@Transactional
public class ManageProductService implements ManageProductUseCase {
        private final ProductoRepositoryPort productoRepositoryPort;
        private final CategoriaRepositoryPort categoriaRepositoryPort;
        private final StoragePort storagePort;

        public ManageProductService(ProductoRepositoryPort productoRepositoryPort,
                        CategoriaRepositoryPort categoriaRepositoryPort, StoragePort storagePort) {
                this.productoRepositoryPort = productoRepositoryPort;
                this.categoriaRepositoryPort = categoriaRepositoryPort;
                this.storagePort = storagePort;
        }

        @Override
        public List<ProductResponse> getAllProducts() {
                return productoRepositoryPort.findAll()
                                .stream()
                                .map(this::mapToResponse)
                                .toList();
        }

        @Override
        public ProductResponse createProduct(CreateProductCommand command, MultipartFile imageFile) {
                Categoria categoria = categoriaRepositoryPort.findById(command.categoriaId())
                                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

                String imagenUrl = null;

                // Subir la imagen si se proporciona un archivo
                if (imageFile != null && !imageFile.isEmpty()) {
                        imagenUrl = storagePort.uploadFile(imageFile);
                }

                Producto producto = new Producto(
                                null,
                                command.nombre(),
                                command.descripcion(),
                                command.precioBase(),
                                categoria,
                                imagenUrl, // Se puede establecer como null si no se proporciona una imagen
                                true);

                Producto guardado = productoRepositoryPort.save(producto);
                return mapToResponse(guardado);
        }

        @Override
        public ProductResponse updateProduct(Integer id, UpdateProductCommand command, MultipartFile imageFile) {
                Producto existente = productoRepositoryPort.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

                Categoria categoria = categoriaRepositoryPort.findById(command.categoriaId())
                                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

                String imagenUrl = null;

                // Subir la imagen si se proporciona un archivo
                if (imageFile != null && !imageFile.isEmpty()) {
                        imagenUrl = storagePort.uploadFile(imageFile);
                }

                Producto productoActualizado = new Producto(
                                existente.id(),
                                command.nombre(),
                                command.descripcion(),
                                command.precioBase(),
                                categoria,
                                imagenUrl,
                                command.isActive());

                Producto guardado = productoRepositoryPort.save(productoActualizado);
                return mapToResponse(guardado);
        }

        @Override
        public String toggleProductStatus(Integer id) {
                Producto producto = productoRepositoryPort.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

                Producto productoModificado = new Producto(
                                producto.id(),
                                producto.nombre(),
                                producto.descripcion(),
                                producto.precioBase(),
                                producto.categoria(),
                                producto.imagenUrl(),
                                !producto.isActive());

                productoRepositoryPort.save(productoModificado);
                return productoModificado.isActive() ? "Producto activado" : "Producto desactivado";
        }

        // Metodo para mapear un objeto Producto a ProductResponse
        private ProductResponse mapToResponse(Producto p) {
                CategoryResponse catResp = p.categoria() != null
                                ? new CategoryResponse(p.categoria().id(), p.categoria().nombre(),
                                                p.categoria().isActive())
                                : null;

                return new ProductResponse(
                                p.id(),
                                p.nombre(),
                                p.descripcion(),
                                p.precioBase(),
                                p.imagenUrl(),
                                p.isActive(),
                                catResp);
        }
}
