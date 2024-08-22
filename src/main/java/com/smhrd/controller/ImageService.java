package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.entity.UploadImg;
import com.smhrd.repository.ImageRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void uploadImage(MultipartFile file) throws IOException {
        UploadImg image = new UploadImg();
        image.setImageData(file.getBytes());

        String fileName = file.getOriginalFilename();
        image.setFileName(fileName);

        long fileSize = file.getSize();
        image.setFileSize(fileSize);

        imageRepository.save(image);
    }

    public List<UploadImg> getAllImages() {
        List<UploadImg> images = imageRepository.findAll();

        // Base64로 인코딩하여 각 이미지 객체에 추가
        for (UploadImg image : images) {
            String base64Image = Base64.getEncoder().encodeToString(image.getImageData());
            image.setBase64Data(base64Image);
        }

        return images;
    }

    public Optional<UploadImg> getImageById(Long id) {
        return imageRepository.findById(id);
    }
}
