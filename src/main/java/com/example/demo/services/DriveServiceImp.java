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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
        
       
        Path filePath = Paths.get(currentDirectory,"");
       
        return filePath.toString();
    }
@Async
    public void uploadImageToDrive(File file,AppUser appuser,Subject subject, String titre) throws GeneralSecurityException, IOException {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
      
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
    
          InputStream in = new ByteArrayInputStream(privateKeyJson().getBytes());

        GoogleCredential credential = GoogleCredential.fromStream(in)
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

public String privateKeyJson(){

    String privateKeyJson="{  \"type\": \"service_account\",\r\n" + //
                "  \"project_id\": \"apptest-402315\",\r\n" + //
                "  \"private_key_id\": \"7b00d59644eebb182b4c368b7f09644b0422872a\",\r\n" + //
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\n" + //
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDOQC+9njaUlslI\\n" + //
                "dPp2MP3CRkjG46U9YS3wsScblvo6glOKTIkoldODzcRJxE8iT2t5eIVbQx6ZtUdq\\n" + //
                "lDDHbsp6XbLSGpppJGL18tCLv4yaTaLdP+Qmma4Y/1o7gvS2sFp5V+7fcPeI7sI0\\n" + //
                "2eWbg3LFi+V+bFRXocaNWA+Qs6cgM/EMS9zMFWovf3hTyIQ+yw4drS2boDM7wsLz\\n" + //
                "0Ld7IH5HcAAoKPmayPXCTgqzsEiBqzgcM3u3jWqbqKINRaV57Y2H/mHElBSv4oxe\\n" + //
                "2ZdGDg3NQjJMoqEwZVIlw/RoGrrEBq+my04KpZ/GqJVIPJFh6o4oAzbmXg9OoVPh\\n" + //
                "xdnngTuLAgMBAAECggEAYBfbpla+S0Bti0qlHsf4krk4UyHg7NYIF2oSNHKFJxlt\\n" + //
                "XrQF+1jC8Ve/KjdCP1QNWMzkJP8ClM629VsR1vkxcQ2SsP0gD/iZ+skkjNez5Hhj\\n" + //
                "dv27iJh1WsQuACglZ7CGlp53drclPTEwXnte1LPLl2p0hBBow2NmJJhwuBRbu05h\\n" + //
                "EKs3QkEDclS4/bdvLhMb+mXmXq9RVISTkgxl/tjHOfTREwL4v0+0vKvS9lHInPY+\\n" + //
                "fag8MTeFPsEz+FJV/T/CIt2OufipyM3lJKE1PwnfgL+qZrTlCHpkoD0Xatv13dIA\\n" + //
                "5XXmx1ZQwGNQRyCKl+0dIAodoftTXfmVGGAPX9N6QQKBgQD6CAgF6eiBBVZn3Ymp\\n" + //
                "Wu3RgWXb9n7/uDgj27pMEuAE/nRk7pMCh62EkVt9H71BEArTnwm2T0UZkTExFPD3\\n" + //
                "dznrLn+SgVcrtKOiB0DS19i1ForvgiSdGkfMZimZcb3fJXxo/Qey2ShYItPcY42E\\n" + //
                "vIjkLeWVUDmz7UpYuS7WJGc5OwKBgQDTLJtetdaKVEwmKABnHB57Oh5dU9tckFHT\\n" + //
                "rIUW2gAzX1FVXr5lMGFy7pMCzZvggHnY2B/hEAz4Vbfs+Y1jHErFY89zhx7lpiZe\\n" + //
                "ATiJIte98CdlnYrpstGjGrCtOj7eylKu9z5Dd/CthIHmB5JPoYtGGJK56pQfVtW9\\n" + //
                "WfIeTp9h8QKBgQDaxiw/AGxweDsiprXNecKdjpuR3B1Do+17csePyPQ7ampt5lK7\\n" + //
                "4/vwA0AKO7tdMqxGfuIAo1vTAzgHuksePTRwlD8L8iBUHbsQC0itNDBqQ09OQpHG\\n" + //
                "5TftoXbEzMFWf3ZcZwpVc63ZcJ/wNyuc+VrOTVqVS50x0m87Ms3IkhHAAQKBgHBx\\n" + //
                "QAmeKS5e1G08v3onrtNOjvyCujr/XZKoOfWA/rpb9qLOX/LhYAIipoTHlkhkIH0m\\n" + //
                "F1NviHhyrAyylGZBmk6gZsXNHNaLMcK8Q65Brz6jsVaaHG1zmcyPWaPjb7aRZJVl\\n" + //
                "cPHb23uJIuGpBm8ARXh9fSMhlN1bD1RToGgi1RsBAoGADV0CKJRWP3/0sAyoT8p6\\n" + //
                "KANKch4MQXAZ7l1p66K1CTpioLjG9v/6YBCJLDFqt1MvXK3o/EJLq99XuQDS7AXz\\n" + //
                "Vx+abTAPPK9xmI4ujge/W92wiyFPktUq9wCIDWy6QNAKWnYOdIQJvIoRY7A2nZT6\\n" + //
                "J8WrInsNYcUr1NwI46/YoBg=\\n" + //
                "-----END PRIVATE KEY-----\\n" + //
                "\",\r\n" + //
                "  \"client_email\": \"googledriveupload@apptest-402315.iam.gserviceaccount.com\",\r\n" + //
                "  \"client_id\": \"109173259945246272045\",\r\n" + //
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n" + //
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n" + //
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n" + //
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/googledriveupload%40apptest-402315.iam.gserviceaccount.com\",\r\n" + //
                "  \"universe_domain\": \"googleapis.com\"}";

    return privateKeyJson;

}


}
