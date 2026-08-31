package com.api.covoshcoffe.common.infrastructure.output;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.api.covoshcoffe.common.application.ports.output.StoragePort;

@Component
public class SupabaseStorageAdapter implements StoragePort {
    private final RestClient restClient;
    private final String supabaseUrl;
    private final String supabaseKey;
    private final String bucket;

    public SupabaseStorageAdapter(
            @Value("${supabase.url}") String supabaseUrl,
            @Value("${supabase.key}") String supabaseKey,
            @Value("${supabase.bucket}") String bucket) {
        this.supabaseUrl = supabaseUrl;
        this.supabaseKey = supabaseKey;
        this.bucket = bucket;
        this.restClient = RestClient.builder().build();
    }

    @Override
    // Subir un archivo al almacenamiento y devolver la URL del archivo subido
    public String uploadFile(MultipartFile file) {
        try {
            String extension = getExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID() + extension;

            // Construir la URL de carga para Supabase Storage
            String uploadUrl = String.format("%s/storage/v1/object/%s/%s", supabaseUrl, bucket, fileName);

            restClient.post()
                    .uri(uploadUrl)
                    .header("Authorization", "Bearer " + supabaseKey)
                    .header("apiKey", supabaseKey)
                    .contentType(MediaType.parseMediaType(
                            file.getContentType() != null ? file.getContentType() : "image/jpeg"))
                    .body(file.getBytes())
                    .retrieve()
                    .toBodilessEntity();

            // Devolver la URL pública del archivo subido
            return String.format("%s/storage/v1/object/public/%s/%s", supabaseUrl, bucket, fileName);

        } catch (IOException e) {
            throw new RuntimeException("Error al leer los bytes de la imagen", e);
        }
    }

    // Método auxiliar para obtener la extensión del archivo
    private String getExtension(String originalName) {
        if (originalName != null && originalName.contains(".")) {
            return originalName.substring(originalName.lastIndexOf("."));
        }
        return ".jpg";
    }
}
