//package com.smhrd.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/")
//public class ImageController {
//
//    private final ImageService imageService;
//
//    @Autowired
//    public ImageController(ImageService imageService) {
//        this.imageService = imageService;
//    }
//
//    @GetMapping("/")
//    public String showUploadForm() {
//        return "image-test.html"; // upload.html 템플릿을 보여줍니다.
//    }
//
//    @PostMapping("/upload")
//    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//        // 파일 이름을 파라미터로 받아서 이미지 서비스로 전달
//        String fileName = file.getOriginalFilename();
//        imageService.uploadImage(fileName, file);
//
//        return "image-test";
//
//    }
//
//    @GetMapping("/image-list")
//    public String listImages(Model model) {
//        List<Image> images = imageService.getAllImages();
//        model.addAttribute("images", images); // Thymeleaf에 이미지 목록을 전달
//        return "image-list"; // Thymeleaf 템플릿 이름 반환
//    }
//
//
//    @GetMapping("/image/{id}")
//    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
//        Optional<Image> image = imageService.getImageById(id);
//        if (image.isPresent()) {
//            byte[] imageData = image.get().getData();
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG); // 이미지 유형에 맞게 수정
//            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//}
