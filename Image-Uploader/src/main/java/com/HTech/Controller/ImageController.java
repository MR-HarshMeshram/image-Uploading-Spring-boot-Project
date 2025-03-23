package com.HTech.Controller;

import com.HTech.Model.ProductImages;
import com.HTech.Services.ImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    @Autowired
    ImageServices service;
    private  String hel(){
        return "hello";
    }
    @PostMapping("/uploadImage")
    public ResponseEntity<?> addProduct(@RequestPart("imageFile") MultipartFile imageFile) {
        System.out.println("File Name: " + imageFile.getOriginalFilename());
        System.out.println("File Size: " + imageFile.getSize());
        System.out.println("Content Type: " + imageFile.getContentType());
        try {
            ProductImages productImage = service.addProductImage(imageFile);
            return new ResponseEntity<>(productImage, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
