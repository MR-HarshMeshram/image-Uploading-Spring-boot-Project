package com.HTech.Services;

import com.HTech.Model.ProductImages;
import com.HTech.Repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServices {
    @Autowired
    ImageRepo rep;
    public ProductImages addProductImage(MultipartFile imageFile) throws IOException {
        // Validate input file
        if (imageFile == null || imageFile.isEmpty()) {
            throw new IllegalArgumentException("Image file is empty or null");
        }

        // Create ProductImage entity and populate it
        ProductImages product = new ProductImages();
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        // Save product object with image data to the repository
        return rep.save(product);
    }
}
