package com.example.uploadingfiles;

import com.example.uploadingfiles.image.Image;
import com.example.uploadingfiles.image.ImageDbRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class ImageController {

    private final ImageDbRepository imageDbRepository;

    @Autowired
    public ImageController(ImageDbRepository imageDbRepository) {
        this.imageDbRepository = imageDbRepository;
    }

    @PostMapping("/image")
    Long uploadImage(@RequestParam MultipartFile multipartImage) throws Exception {
        Image image = new Image();
        image.setName(multipartImage.getName());
        image.setContent(multipartImage.getBytes());

        return imageDbRepository.save(image).getId();
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@PathVariable Long imageId) {
        byte[] image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(image);
    }

}
