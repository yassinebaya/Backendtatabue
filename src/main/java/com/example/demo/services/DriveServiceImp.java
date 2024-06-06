package com.example.demo.services;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.example.demo.entites.AppUser;
import com.example.demo.entites.Liens;
import com.example.demo.entites.Subject;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.LienRepo;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
@AllArgsConstructor
public class DriveServiceImp implements DriveService {
    @Autowired
    LienRepo lienRepo;
    @Autowired
    AppUserRepository appuserReository;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACOUNT_KEY_PATH = getPathToGoodleCredentials();

    private static String getPathToGoodleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "cred.json");
       
        return filePath.toString();
    }

    public void uploadImageToDrive(File file,AppUser appuser,Subject subject, String titre) throws GeneralSecurityException, IOException {
      
        try{
            String folderId = "1u7RReYcB-RbNl2ny6gXkxu48_miWOSUU";
            Drive drive = createDriveService();
            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(file.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("application/pdf", file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id").execute();
            String imageUrl = "https://drive.google.com/uc?export=view&id="+uploadedFile.getId();
            saveLiens(uploadedFile.getId(),appuser,subject,titre);
            System.out.println("IMAGE URL: " + imageUrl);
            file.delete();
            
        }catch (Exception e){
            System.out.println(e.getMessage());
           
        }


    }


    public void deleteFileFromDrive(String fileId) throws GeneralSecurityException, IOException {
        Drive drive = createDriveService();
        try {
            drive.files().delete(fileId).execute();
            System.out.println("File with ID: " + fileId + " has been deleted from Google Drive.");
        } catch (IOException e) {
            System.err.println("Error deleting file from Drive: " + e.getMessage());
            throw e;
        }
    }





    private Drive createDriveService() throws GeneralSecurityException, IOException {

        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .build();

    }

    public void saveLiens(String lien,AppUser appuser,Subject subject, String titre){
       Liens liens=new Liens();
      liens.setLien(lien);
      liens.setAppUser(appuser);
      liens.setSubject(subject);
      liens.setTitre(titre);
     lienRepo.save(liens);
		
	}

}
