package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.example.demo.entites.AppUser;
import com.example.demo.entites.Subject;

public interface DriveService {

    public void uploadImageToDrive(File file,AppUser appuser,Subject subject,String Titre)throws GeneralSecurityException, IOException;

    public void deleteFileFromDrive(String fileId) throws GeneralSecurityException, IOException;

}
