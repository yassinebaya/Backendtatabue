package com.example.demo.controller;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dtos.Responce;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entites.AppUser;
import com.example.demo.mappers.Maperuser;
import com.example.demo.service.AccoubtService;

@RestController
@CrossOrigin("*")
public class LoginController {
 @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private AccoubtService accoubtService;
    @Autowired
     private Maperuser maperuser;
   
    Responce responce;
 //   @PostMapping("/login")
    public Object login(String username, String password){
           
         AppUser appUser=accoubtService.loadAppUserByname(username);
          Authentication authentication= authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username,password));
             SecurityContextHolder.getContext().setAuthentication(authentication);
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

    
}
