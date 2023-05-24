package com.imageBoardAI.boardai.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public interface ImageService {
     String saveImage(MultipartFile imageFile) throws IOException;
}
