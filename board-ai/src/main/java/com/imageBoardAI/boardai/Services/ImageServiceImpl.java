package com.imageBoardAI.boardai.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{

    private static final String UPLOAD_DIR = "/Desktop/Image Board files/images";

    public String saveImage(MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException("Image file is empty");
        }

        String originalFileName = imageFile.getOriginalFilename();
        String fileName = generateUniqueFileName(originalFileName);
        String filePath = UPLOAD_DIR + File.separator + fileName;

        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(imageFile.getBytes());
        }

        return filePath;
    }

    private String generateUniqueFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String fileExtension = getFileExtension(originalFileName);
        return uuid + fileExtension;
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return lastDotIndex > 0 ? fileName.substring(lastDotIndex) : "";
    }

}
