package com.example.demo.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entites.AppUser;
@Service
public class Maperuser  {

    public UserDTO fromUser(AppUser user){

        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;

    }
    
}
