package com.example.demo.controller;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entites.AppUser;
import com.example.demo.entites.Assistant;
import com.example.demo.entites.Inscriptions;
import com.example.demo.entites.Stagaire;
import com.example.demo.mappers.Maperuser;
import com.example.demo.repo.AppUserRepository;
import com.example.demo.repo.InscriptionRepository;
import com.example.demo.service.AccoubtService;
import com.example.demo.service.ReportService;
import com.example.demo.services.StorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class TestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private AccoubtService accoubtService;
    @Autowired
    private Maperuser maperuser;
    @Autowired
    private ReportService reportService;
        @Autowired
     private AppUserRepository appUserRepository;
     @Autowired
     private StorageService service;
    private Inscriptions inscriptions;
    @Autowired
    private InscriptionRepository inscriptionRepository;
    
      @GetMapping("/Test1")
      @PreAuthorize("hasAuthority('SCOPE_USER')")
      public ResponseEntity<byte[]> Btest1(String username){
        return reportService.generateReport();
      }

      @GetMapping("/Test12")
      @PreAuthorize("hasAuthority('SCOPE_USER')")
      public List<AppUser> Btest(String username){
        System.out.println(username);
        List<AppUser> appUser = appUserRepository.findAll();
        return appUser;
      }

      @PostMapping("/addnewrole")
      @PreAuthorize("hasAuthority('SCOPE_USER')")
      public String addnewrole(String role){
               accoubtService.addnewRole(role);
        return role + "est ajouter sur la base de donné";
      }
      @PostMapping("/ajouterinscription")
      // @PreAuthorize("hasAuthority('SCOPE_USER')")
        public Inscriptions ajouterinscription(String username,String email,String nom,String tel){
                  Inscriptions inscriptions=new Inscriptions();
            inscriptions.setNomuser(username);
            inscriptions.setEmail(email);
            inscriptions.setNom(nom);
            inscriptions.setTel(tel);
             inscriptionRepository.save(inscriptions);
            return inscriptions;
        }
        @PostMapping("/ajouterassistant")
   public Assistant ajouterassistant(String username,String password,String email,String nom,String tel){
         Assistant assistant=new Assistant();
         assistant.setUsername(username);
         assistant.setPassword(password);
         assistant.setEmail(email);
         assistant.setNom(nom);
         assistant.setTel(tel);
         appUserRepository.save(assistant); 
    return assistant;
}

        @GetMapping("/listestagaire")
        // @PreAuthorize("hasAuthority('SCOPE_USER')")
          public  Page<Stagaire> liststagiaire(int page ,int size){
           
            Pageable pageable = PageRequest.of(page, size);
                    Page<Stagaire> listsStagaires=appUserRepository.findByStagaires(pageable);
                    System.out.println(listsStagaires);
              return listsStagaires;
          }
          @GetMapping("/listassistant/{page}/{size}")
        // @PreAuthorize("hasAuthority('SCOPE_USER')")
          public Page<Assistant> listassistant(@PathVariable int page,@PathVariable int size){
            Pageable pageable = PageRequest.of(page,size);
                    Page<Assistant> listsassistant=appUserRepository.findByAssistants(pageable);
                    System.out.println(listsassistant);
              return listsassistant;
          }
      @PostMapping("/addusertorol")
      // @PreAuthorize("hasAuthority('SCOPE_USER')")
        public AppUser addusertorol(String username,String rol){
                 accoubtService.addRoleToUser(username,rol);
               AppUser  appUser=appUserRepository.findByUsername(username);
               System.out.println(appUser);
                 return appUser;
        }
      @PostMapping("/addnewuser")
    // @PreAuthorize("hasAuthority('SCOPE_USER')")
      public String addnewuser(String username,String password){
               accoubtService.addNewUser(username,password);
              
               return username+"est ajouté" ;
      }
    
      @PostMapping("/Inscription")
    // @PreAuthorize("hasAuthority('SCOPE_USER')")
      public AppUser inscription(String numdossier,String email,String password){

        return accoubtService.Activercompte(numdossier,email,password);
      }

	@PostMapping("/uploadImag")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("downloadImage/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] imageData=service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_PDF)
				.body(imageData);

	}
    
  @PostMapping("/objet")
  // @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<Assistant>  objet(@RequestBody List<UserDTO> employeeDetails){
     List<Assistant> stagaires=new ArrayList<>();
             for(UserDTO appUser:employeeDetails){
              System.out.println(appUser.getId());
              Assistant stage=appUserRepository.findById(appUser.getId());
              System.out.println(stage);
                       stage.setUsername(appUser.getUsername());
                       stagaires.add(stage);
             }
             appUserRepository.saveAll(stagaires);
             return stagaires;
    }

    @PostMapping("/login")
    public UserDTO login(String username, String password){
         AppUser appUser=accoubtService.loadAppUserByname(username);
          Authentication authentication= authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username,password));
        Instant instant=Instant.now();
        String scope=authentication.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                .subject(username)
                .claim("scope",scope)
                .build();
        JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(),jwtClaimsSet);
String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
UserDTO userDTO=maperuser.fromUser(appUser);
userDTO.setJwt(jwt);

return userDTO ;


    }

    @GetMapping("/Test")
    public  Object[] demerrertest() {
        Object[] partnerIds = new Object[0];
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

            // Odoo server URL
            config.setServerURL(new URL("http://localhost:8069/xmlrpc/2/common"));

            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            // Authentication
            String db = "tatabue";
            String username = "yassinebenayad@gmail.com";
            String password = "1234";

            List<Object> emptyList = Arrays.asList();
            ;
            Integer uid = (Integer) client.execute("authenticate", Arrays.asList(
                    db, username, password, emptyList)
            );
            System.out.println(uid);
            config.setServerURL(new URL("http://localhost:8069/xmlrpc/2/object"));
            XmlRpcClient client2 = new XmlRpcClient();
            client2.setConfig(config);

            // Example: Reading records from Odoo's 'res.partner' model
            HashMap<String, Object> searchReadParams = new HashMap<>();
            searchReadParams.put("fields", Arrays.asList("country_id", "comment"));
            partnerIds = (Object[]) client2.execute("execute_kw", Arrays.asList(
                    db, uid, password,
                    "res.partner", "search_read", Arrays.asList(Arrays.asList(
                            Arrays.asList("id", "<>", 0))), searchReadParams
            ));

            for (Object partnerId : partnerIds) {
                System.out.println("Partner: " + partnerId);
            }

        } catch (XmlRpcException | java.net.MalformedURLException e) {
            e.printStackTrace();
        }

        return partnerIds;
    }


}
