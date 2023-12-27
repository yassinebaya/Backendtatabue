
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entites.AppUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailserviceimpl implements UserDetailsService {
@Autowired
    private AccoubtService accoubtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=accoubtService.loadAppUserByname(username);
        if (appUser==null) throw new UsernameNotFoundException(String.format("User %s not found", username));
        String[] roles=appUser.getAppRoles().stream().map(u->u.getRolename()).toArray(String[]::new);
        UserDetails userDetails=User
                             
                               .withUsername(appUser.getUsername())
                               .password(appUser.getPassword())
                               .roles(roles)
                               .build();
            
        return userDetails;


      
    }


    
}