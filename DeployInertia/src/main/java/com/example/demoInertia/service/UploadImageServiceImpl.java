package com.example.demoInertia.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demoInertia.model.Images;
// import com.example.demoInertia.dto.APIResponse;
import com.example.demoInertia.repository.UploadImageRepository;

@Service
public class UploadImageServiceImpl implements UploadImageService{

    @Autowired
    private UploadImageRepository uploadImageRepository;

    @Override
    public void uploadFile(MultipartFile file) throws IOException {

        

        String uploadDir ="D:/Users/User/Documents/DemoInertiaImages";


        String fileName = UUID.randomUUID().toString() + ".jpg";
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());

        // Save the uploaded file to the new file location
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save the file path in your database
        String imagePath = filePath.toAbsolutePath().toString();

        Images image = new Images();
        image.setAddress(imagePath);
        image.setName(fileName);
        // image.setOrganization(organization);
        uploadImageRepository.save(image);
         

        // return (new APIResponse());

    }
    
}
