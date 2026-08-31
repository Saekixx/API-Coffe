package com.api.covoshcoffe.common.application.ports.output;

import org.springframework.web.multipart.MultipartFile;

public interface StoragePort {
    // Subir un archivo al almacenamiento y devolver la URL del archivo subido
    String uploadFile(MultipartFile file);
}
