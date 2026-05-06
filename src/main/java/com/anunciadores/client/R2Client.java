package com.anunciadores.client;

import software.amazon.awssdk.services.s3.S3Client;

public class R2Client {
    private final S3Client s3Client;

    public R2Client(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public S3Client getClient() {
        return s3Client;
    }
}
