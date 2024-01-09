package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.demo.entites.AppUser;
import com.example.demo.repo.AppUserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional
@AllArgsConstructor
public class ReportService {
     private AppUserRepository appUserRepository;

     public ResponseEntity<byte[]> generateReport() {
        try {
            // Fetch articles from repository
           List<AppUser> appUser = appUserRepository.findAll();

            // Load JRXML file
            File file = ResourceUtils.getFile("classpath:Coffee_Landscape.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Prepare data source
            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(appUser);

            // Set parameters for the report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "My self");

            // Fill the report
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, datasource);

            // Convert JasperPrint to byte array (PDF)
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
            byte[] reportBytes = byteArrayOutputStream.toByteArray();

            // Set response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");

            // Return the report as a ResponseEntity
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(reportBytes.length)
                    .body(reportBytes);
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
