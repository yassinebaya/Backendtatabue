package com.example.demo.controller;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entites.AppUser;
import com.example.demo.entites.Subject;
import com.example.demo.services.DriveService;

@RestController
@CrossOrigin("*")
public class UploaddriveController {
      @Autowired
         DriveService service;
       @PostMapping("/uploadToGoogleDrive")
    public Object handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam AppUser iduser,@RequestParam Subject idsubject,@RequestParam String titre) throws IOException, GeneralSecurityException {
        if (file.isEmpty()) {
            return "FIle is empty";
        }
        File tempFile = File.createTempFile("pdf", ".pdf");
        file.transferTo(tempFile);
         service.uploadImageToDrive(tempFile,iduser,idsubject,titre);
      
        return null;
    }
    
}
