package com.anunciadores.service;
import com.anunciadores.client.R2Client;
import com.anunciadores.service.interfaces.IR2UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;


@Service
public class R2UploadService implements IR2UploadService {


    private final R2Client r2Client;

    @Value("${cloudflare.r2.bucket}")
    private String bucket;

    @Value("${cloudflare.r2.public-url}")
    private String publicUrl;

    public R2UploadService(R2Client r2Client,
                           @Value("${cloudflare.r2.bucket}") String bucket,
                           @Value("${cloudflare.r2.public-url}") String publicUrl) {
        this.r2Client = r2Client;
        this.bucket = bucket;
        this.publicUrl = publicUrl;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        // Usas el cliente a través del wrapper
        r2Client.getClient().putObject(request,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return publicUrl + "/" + fileName;
    }
}
