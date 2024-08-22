package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.entity.UploadImg;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/")
    public String showUploadForm() {
        return "fileUpload"; 
    }

    @RequestMapping("/fileUpload")
    public String openfileUpload() {
        return "fileUpload"; 
    }

    @RequestMapping("/fileList")
    public String openfileList() {
        return "fileList"; 
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        imageService.uploadImage(file);
        return "redirect:/fileList";
    }

    @GetMapping("/fileList")
    public String listImages(Model model) {
        List<UploadImg> images = imageService.getAllImages();
        model.addAttribute("images", images);
        return "fileList";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<UploadImg> image = imageService.getImageById(id);
        if (image.isPresent()) {
            byte[] imageData = image.get().getImageData();
            HttpHeaders headers = new HttpHeaders();
            
            // MIME 타입을 동적으로 결정
            String mimeType = "image/jpeg"; // 기본값을 JPEG로 설정
            String fileName = image.get().getFileName();
            if (fileName.endsWith(".png")) {
                mimeType = "image/png";
            } else if (fileName.endsWith(".gif")) {
                mimeType = "image/gif";
            } else if (fileName.endsWith(".bmp")) {
                mimeType = "image/bmp";
            }
            headers.setContentType(MediaType.parseMediaType(mimeType));
            
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
