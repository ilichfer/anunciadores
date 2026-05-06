package com.anunciadores.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface IR2UploadService {

public String uploadImage(MultipartFile file) throws IOException;

}
