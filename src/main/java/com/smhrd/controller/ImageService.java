//package com.smhrd.controller;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.smhrd.repository.ImageRepository;
//
//@Service
//public class ImageService {
//    private final ImageRepository imageRepository;
//
//    @Autowired
//    public ImageService(ImageRepository imageRepository) {
//        this.imageRepository = imageRepository;
//    }
//    public void uploadImage(String fileName, MultipartFile file) throws IOException {
//        Image image = new Image();
//        image.setFileName(fileName);
//        image.setData(file.getBytes());
//        imageRepository.save(image);
//    }
//
//    public List<Image> getAllImages() {
//        return imageRepository.findAll();
//    }
//
//    // 이미지 조회 메서드
//    public Optional<Image> getImageById(Long id) {
//        return imageRepository.findById(id);
//    }
//
//}
